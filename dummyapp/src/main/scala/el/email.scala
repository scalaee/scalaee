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

import javax.faces.application.FacesMessage
import javax.faces.component.UIComponent
import javax.faces.context.FacesContext
import javax.faces.convert.{ Converter, ConverterException, FacesConverter }


case class EMail(user: String, domain: String)


@FacesConverter(forClass = classOf[EMail])
class EmailConverter extends Converter {

  val eMailPattern = """(\w+)@(\w+\.\w+)""".r

  def getAsObject(context: FacesContext, component: UIComponent, value: String): EMail = value match {
    case eMailPattern(user, domain) => EMail(user, domain)
    case _ => throw new ConverterException(new FacesMessage("""Invalid format! Email must stick to pattern ".+@.+\..+"!"""))
  }

  def getAsString(context: FacesContext, component: UIComponent, value: AnyRef): String = {
    val email =  value.asInstanceOf[EMail]
    "%s@%s".format(email.user, email.domain)
  }
}
