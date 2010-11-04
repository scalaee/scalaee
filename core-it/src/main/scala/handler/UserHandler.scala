/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaeeit
package handler

import model._

import com.weiglewilczek.slf4s.Logging
import javax.faces.bean.{ ManagedBean, SessionScoped }

@ManagedBean
@SessionScoped
class UserHandler extends Logging {

  var user = new User with NamedUser

  def save: String = {
    logger.debug("""Trying to save user with email "%s".""" format user.email)
    "/index"
  }
}
