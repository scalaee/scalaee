/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaeeit
package model

class User(
  var email: Email = null,
  var password: String = null)

trait NamedUser {
  this: User =>

  var firstName: String = _

  var lastName: String = _
}
