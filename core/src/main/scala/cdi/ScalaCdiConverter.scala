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

class ScalaCdiConverter extends Extension {

  //private val logger = Logger.getLogger(getClass.getName)

  private def log(msg: String) {
    //logger.log(Level.WARNING, msg)
  }

  def beforeBeanDiscovery(@Observes bbd: BeforeBeanDiscovery) {
    //logger.info("X#X#X#X#X#X#X#X#X BeforeBeanDiscovery")
  }

  def processAnnotatedType[A](@Observes pat: ProcessAnnotatedType[A]) {
    //logger.info("X#X#X#X#X#X#X#X#X ProcessAnnotatedType:" + pat.getAnnotatedType)

    if (pat.getAnnotatedType.getJavaClass.getName.endsWith("BeanWithFinalPublicMethod")) {
//      log("##########")
//      log("##########")
//      log("##########")
//      log("##########")
//      log("##########")
//      log("##########")
    }



  }

  def processBean[A](@Observes pb: ProcessBean[A]) {
    //logger.info("X#X#X#X#X#X#X#X#X ProcessBean:" + pb.getBean)
  }

  def processInjectionTarget[A](@Observes pit: ProcessInjectionTarget[A]) {
    //logger.info("X#X#X#X#X#X#X#X#X ProcessInjectionTarget:" + pit.getAnnotatedType)
  }

  def afterBeanDiscovery(@Observes abd: AfterBeanDiscovery, beanManager: BeanManager) {
    //logger.info("X#X#X#X#X#X#X#X#X AfterBeanDiscovery")
  }

}
