/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaeeit
package model

import javax.faces.application.FacesMessage
import javax.faces.component.UIComponent
import javax.faces.context.FacesContext
import javax.faces.convert.{ Converter, ConverterException, FacesConverter }

object Email {

  val EmailPattern = """(\w+)@(\w+\.\w+)""".r
}

case class Email(user: String, domain: String)

@FacesConverter(forClass = classOf[Email])
class EmailConverter extends Converter {
  import Email._

  def getAsObject(context: FacesContext, component: UIComponent, value: String): Email = {
    try {
      val EmailPattern(user, domain) = value
      Email(user, domain)
    } catch {
      case e: MatchError => throw new ConverterException(new FacesMessage("""Invalid format! Email must stick to pattern ".+@.+\..+"!"""))
    }
  }

  def getAsString(context: FacesContext, component: UIComponent, value: AnyRef): String = {
    val email =  value.asInstanceOf[Email]
    "%s@%s".format(email.user, email.domain)
  }
}
