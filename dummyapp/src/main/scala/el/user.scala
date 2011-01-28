/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaee
package dummyapp
package model

import java.io.Serializable

class User(var email: EMail = null,
           var password: String = "",
           var firstName: String = "",
           var lastName: String = "") extends Serializable
