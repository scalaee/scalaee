/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaee
package util

import com.weiglewilczek.slf4s.Logging
import java.beans.FeatureDescriptor
import java.lang.{ Class, String }
import java.lang.reflect.Method
import java.util.{ ArrayList => JArrayList, Iterator => JIterator }
import javax.el._
import javax.faces.FactoryFinder._
import javax.faces.application.ApplicationFactory
import javax.servlet.annotation.WebListener
import javax.servlet.{ ServletContextEvent, ServletContextListener }

/**
 * Adds a {@link ScalaELResolver} to the JSF application.
 */
@WebListener
private class ScalaELResolverWebListener extends ServletContextListener with Logging {

  /**
   * Adds a {@link ScalaELResolver} to the JSF application.
   */ 
  def contextInitialized(event: ServletContextEvent) {
    try {
      val applicationFactory = getFactory(APPLICATION_FACTORY).asInstanceOf[ApplicationFactory]
      val resolver = applicationFactory.getApplication.getELResolver.asInstanceOf[CompositeELResolver]
      val resolversField = classOf[CompositeELResolver] getDeclaredField "elResolvers"
      resolversField.setAccessible(true)
      val resolversValue = (resolversField get resolver).asInstanceOf[JArrayList[ELResolver]]
      resolversValue.add(0, new ScalaELResolver)
      resolversField.set(resolver, resolversValue)
      logger.info("Added ScalaELResolver to JSF application.")
    } catch {
      case e => logger.error("Could not add ScalaELResolver to JSF application!", e)
    }
  }

  /**
   * Does nothing!
   */
  def contextDestroyed(event: ServletContextEvent) {}
}

/**
 * Scala style does (luckily) not obey to JavaBean conventions: Accessors aren't prefixed with <i>get</i> and <i>set</i>.
 * Therefore it is necessary to adapt to JavaBean style by means of this special resolver.
 */
private[util] class ScalaELResolver extends ELResolver with Logging {
  // TODO Must apply only to instances of ScalaObject!

  def getType(context: ELContext, base: AnyRef, property: AnyRef): Class[_] =
    applyToProperty(context, base, property.asInstanceOf[String], _.getReturnType)

  def getValue(context: ELContext, base: AnyRef, property: AnyRef): AnyRef =
    applyToProperty(context, base, property.asInstanceOf[String], _ invoke base)

  def setValue(context: ELContext, base: AnyRef, property: AnyRef, value: AnyRef) {
    if (base != null) {
      val name = property.asInstanceOf[String] + "_$eq"
      try {
        base.getClass.getMethod(name, value.getClass).invoke(base, value)
        context.setPropertyResolved(true)
        logger.debug("""Successfully resolved property "%s" for base class "%s".""".format(name, base.getClass))
      } catch {
        case e: NoSuchMethodException => {
          logger.debug("""Could not resolve property "%s" for base class "%s".""".format(name, base.getClass))
          null
        }
      }
    }
    else logger.debug("""Cannot resolve properties for a null base.""")
  }

  def getCommonPropertyType(context: ELContext, base: AnyRef): Class[_] =
    throw new UnsupportedOperationException("This method is hopefully irrelevant for the ScalaELResolver.")

  def getFeatureDescriptors(context: ELContext, base: AnyRef): JIterator[FeatureDescriptor] =
    throw new UnsupportedOperationException("This method is hopefully irrelevant for the ScalaELResolver.")

  def isReadOnly(context: ELContext, base: AnyRef, property: AnyRef): Boolean = {
    throw new UnsupportedOperationException("This method is hopefully irrelevant for the ScalaELResolver.")
  }

  private def applyToProperty[A >: Null](
      context: ELContext,
      base: AnyRef,
      name: => String,
      f: Method => A): A = {
    if (base != null) {
      try {
        val a = f(base.getClass getMethod name)
        context.setPropertyResolved(true)
        logger.debug("""Successfully resolved property "%s" for base class "%s".""".format(name, base.getClass))
        a
      } catch {
        case e: NoSuchMethodException => {
          logger.debug("""Could not resolve property "%s" for base class "%s".""".format(name, base.getClass))
          null
        }
      }
    }
    else {
      logger.debug("""Cannot resolve properties for a null base.""")
      null
    }
  }
}
