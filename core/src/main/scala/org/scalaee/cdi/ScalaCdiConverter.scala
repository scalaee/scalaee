/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaee
package cdi

import javax.enterprise.inject.spi._
import javax.enterprise.event.Observes
import java.util.logging.{Level, Logger}
import org.jboss.seam.solder.reflection.annotated.AnnotatedTypeBuilder

class ScalaCdiConverter extends Extension {

//  private val logger = Logger.getLogger(getClass.getName)
//
//  private def log(msg: String) {
//    logger.log(Level.WARNING, msg)
//  }
//
//  private def castAnnotatedType[A, B](targetType: AnnotatedType[A], raw: AnnotatedType[B]): AnnotatedType[A] = {
//    raw.asInstanceOf[AnnotatedType[A]]
//  }


//  def processAnnotatedType[A](@Observes pat: ProcessAnnotatedType[A]) {
//    if (classOf[ScalaObject].isAssignableFrom(pat.getAnnotatedType.getJavaClass))
//
//    if (pat.getAnnotatedType.getJavaClass.getName.endsWith("XBean")) {
//      log("####################################################################################################################")
//      log("####################################################################################################################")
//      log("####################################################################################################################")
//
//
//
//
//      val newAnnotatedType = Some(pat.getAnnotatedType) flatMap { at =>
//        checkTraitMixins(at)
//      } getOrElse pat.getAnnotatedType
//
//      pat.setAnnotatedType(newAnnotatedType)
//
//      log("####################################################################################################################")
////      pat.veto()
//    }
//
//  }

//  private def checkTraitMixins[A](annotatedType: AnnotatedType[A]): Option[AnnotatedType[A]] = {
////    val originalClass = pat.getAnnotatedType.getJavaClass
////    val proxyClass = Class.forName(originalClass.getName + "Proxy").asInstanceOf[Class[Object]]
////    val proxyAtRaw = new AnnotatedTypeBuilder().readFromType(proxyClass).create()
////    val proxyAtTyped = castAnnotatedType(pat.getAnnotatedType, proxyAtRaw)
//
//    None
//  }

//  def beforeBeanDiscovery(@Observes bbd: BeforeBeanDiscovery) {
//    //logger.info("X#X#X#X#X#X#X#X#X BeforeBeanDiscovery")
//  }
//  def processBean[A](@Observes pb: ProcessBean[A]) {
//    //logger.info("X#X#X#X#X#X#X#X#X ProcessBean:" + pb.getBean)
//  }
//  def processInjectionTarget[A](@Observes pit: ProcessInjectionTarget[A]) {
//    //logger.info("X#X#X#X#X#X#X#X#X ProcessInjectionTarget:" + pit.getAnnotatedType)
//  }
//  def afterBeanDiscovery(@Observes abd: AfterBeanDiscovery, beanManager: BeanManager) {
//    //logger.info("X#X#X#X#X#X#X#X#X AfterBeanDiscovery")
//  }

}
