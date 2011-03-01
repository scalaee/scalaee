/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaee
package el

import com.weiglewilczek.slf4s.Logging
import java.beans.FeatureDescriptor
import java.lang.{ Class, String }
import java.lang.reflect.Method
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
      applicationFactory.getApplication.addELResolver(new ScalaELResolver)
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
private[el] class ScalaELResolver extends ELResolver with Logging {

  private def applyToProperty[A >: Null](
      context: ELContext,
      base: AnyRef,
      methodName: String,
      methodArgument: Option[Class[_]],
      callback: Method => A): A = {
    base match {
      case so: ScalaObject => {
        try {
          val method = methodArgument match {
            case Some(v) => so.getClass.getMethod(methodName, v)
            case None => so.getClass.getMethod(methodName)
          }
          val result = callback(method)
          context.setPropertyResolved(true)
          logger.debug("""Successfully resolved "%s" for base class "%s".""".format(methodName, base.getClass))
          result
        } catch {
          case e: NoSuchMethodException => {
            logger.debug("""Could not resolve "%s" for base class "%s".""".format(methodName, base.getClass))
            null
          }
        }
      }
      case _ => null
    }
  }

  def getType(context: ELContext, base: AnyRef, property: AnyRef): Class[_] = {
    val name = property.asInstanceOf[String]
    applyToProperty(context, base, name, None, _.getReturnType)
  }

  def getValue(context: ELContext, base: AnyRef, property: AnyRef): AnyRef = {
    val name = property.asInstanceOf[String]
    applyToProperty(context, base, name, None, _ invoke base)
  }

  def setValue(context: ELContext, base: AnyRef, property: AnyRef, value: AnyRef) {
    val name = property.asInstanceOf[String] + "_$eq"
    applyToProperty(context, base, name, Some(value.getClass), _.invoke(base, value))
  }

  def getCommonPropertyType(context: ELContext, base: AnyRef): Class[_] =
    throw new UnsupportedOperationException("This method is hopefully irrelevant for the ScalaELResolver.")

  def getFeatureDescriptors(context: ELContext, base: AnyRef): Iterator[FeatureDescriptor] =
    throw new UnsupportedOperationException("This method is hopefully irrelevant for the ScalaELResolver.")

  def isReadOnly(context: ELContext, base: AnyRef, property: AnyRef): Boolean = {
    throw new UnsupportedOperationException("This method is hopefully irrelevant for the ScalaELResolver.")
  }


}
