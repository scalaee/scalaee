/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaee
package dummyapp
package handler

import model._

import javax.inject.Named
import com.weiglewilczek.slf4s.Logger
import javax.enterprise.context.SessionScoped



// ---------------------------------------------------------

trait BeanWithMethod {
  def method(): String
}

@SessionScoped
@serializable
class BeanWithFinalPublicMethod extends BeanWithMethod {
  final def method() = "bla"
}

@SessionScoped
@serializable
class BeanWithPublicMethod extends BeanWithMethod {
  def method() = "bla"
}

// ---------------------------------------------------------


@Named
@SessionScoped
@serializable
class UserHandler {

  //private val logger = Logger.getLogger(getClass.getName)

  var user = new User

  def save: String = {
    //logger.debug("""Trying to save user with email "%s".""" format user.email)
    "/index"
  }
}
