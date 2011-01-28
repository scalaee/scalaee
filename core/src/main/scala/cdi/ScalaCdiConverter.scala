/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaee
package cdi

import com.weiglewilczek.slf4s.Logger
import javax.enterprise.inject.spi._
import javax.enterprise.event.Observes


class ScalaCdiConverter extends Extension {

  private val logger = Logger(this.getClass)

  logger.debug("#########################################")
  logger.debug("#########################################")
  logger.debug("#########################################")
  logger.debug("#########################################")
  logger.debug("#########################################")

  def beforeBeanDiscovery(@Observes bbd: BeforeBeanDiscovery) {
    //logger.info("X#X#X#X#X#X#X#X#X BeforeBeanDiscovery")
  }

  def processAnnotatedType[A](@Observes pat: ProcessAnnotatedType[A]) {
    //logger.info("X#X#X#X#X#X#X#X#X ProcessAnnotatedType:" + pat.getAnnotatedType)
  }

  def processInjectionTarget[A](@Observes pit: ProcessInjectionTarget[A]) {
    //logger.info("X#X#X#X#X#X#X#X#X ProcessInjectionTarget")
  }

  def processBean[A](@Observes pb: ProcessBean[A]) {
    //logger.info("X#X#X#X#X#X#X#X#X ProcessBean:" + pb.getBean)
  }

  def afterBeanDiscovery(@Observes abd: AfterBeanDiscovery, beanManager: BeanManager) {
    //logger.info("X#X#X#X#X#X#X#X#X AfterBeanDiscovery")
  }

}
