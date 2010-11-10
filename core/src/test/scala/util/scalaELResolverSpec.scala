/*
 * Copyright (c) 2010 WeigleWilczek and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.scalaee
package util

import javax.el.ELContext
import org.mockito.Matchers._
import org.specs.Specification
import org.specs.mock.Mockito

class ScalaELResolverSpec extends Specification with Mockito {

  "Calling getValue" should {

    val resolver = new ScalaELResolver
    val context = mock[ELContext]
    val child = new Child

    "leave ELContext.propertyResolved alone when called with a null base" in {
      resolver.getValue(context, null, "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

    "leave ELContext.propertyResolved alone when called with a base of type Child and a non-existing property" in {
      resolver.getValue(context, child, "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

//    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pf" """ in {
//      skip("Scala compiler magic: protected Scala members will result in public Java members (see javap Child)!")
//      resolver.getValue(context, child, "pf")
//      there was no(context).setPropertyResolved(anyBoolean)
//    }
//
    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pm" """ in {
      resolver.getValue(context, child, "pm")
      there was no(context).setPropertyResolved(anyBoolean)
    }

//    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pf2" """ in {
//      skip("Scala compiler magic: protected Scala members will result in public Java members (see javap Child)!")
//      resolver.getValue(context, child, "pf2")
//      there was no(context).setPropertyResolved(anyBoolean)
//    }

    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pm2" """ in {
      resolver.getValue(context, child, "pm2")
      there was no(context).setPropertyResolved(anyBoolean)
    }

    """set ELContext.propertyResolved to true and return "f" when called with a base of type Child and a property "f" """ in {
      resolver.getValue(context, child, "f") mustEqual "f"
      there was one(context).setPropertyResolved(true)
    }

//    """set ELContext.propertyResolved to true and return "Array(m)" when called with a base of type Child and a property "m" """ in {
//      skip("Not sure how to handle arrays!")
////      resolver.getValue(context, child, "m").asInstanceOf[Array[String]] must haveTheSameElementsAs Array("m")
////      there was one(context).setPropertyResolved(true)
//    }

    """set ELContext.propertyResolved to true and return "f2" when called with a base of type Child and a property "f2" """ in {
      resolver.getValue(context, child, "f2") mustEqual "f2"
      there was one(context).setPropertyResolved(true)
    }

    """set ELContext.propertyResolved to true and return "m2" when called with a base of type Child and a property "m2" """ in {
      resolver.getValue(context, child, "m2") mustEqual "m2"
      there was one(context).setPropertyResolved(true)
    }

    """set ELContext.propertyResolved to true and return "Name(first, last)" when called with a base of type Child and a property "name" """ in {
      resolver.getValue(context, child, "name") mustEqual Name("first", "last")
      there was one(context).setPropertyResolved(true)
    }

    """set ELContext.propertyResolved to true and return "Name(first2, last2)" when called with a base of type Child and a property "name2" """ in {
      resolver.getValue(context, child, "name2") mustEqual Name("first2", "last2")
      there was one(context).setPropertyResolved(true)
    }
  }

  "Calling getType" should {

    val resolver = new ScalaELResolver
    val context = mock[ELContext]
    val child = new Child

    "leave ELContext.propertyResolved alone when called with a null base" in {
      resolver.getType(context, null, "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

    "leave ELContext.propertyResolved alone when called with a base of type Child and a non-existing property" in {
      resolver.getType(context, child, "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

//    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pf" """ in {
//      skip("Scala compiler magic: protected Scala members will result in public Java members (see javap Child)!")
//      resolver.getType(context, child, "pf")
//      there was no(context).setPropertyResolved(anyBoolean)
//    }

    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pm" """ in {
      resolver.getType(context, child, "pm")
      there was no(context).setPropertyResolved(anyBoolean)
    }

//    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pf2" """ in {
//      skip("Scala compiler magic: protected Scala members will result in public Java members (see javap Child)!")
//      resolver.getType(context, child, "pf2")
//      there was no(context).setPropertyResolved(anyBoolean)
//    }

    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pm2" """ in {
      resolver.getType(context, child, "pm2")
      there was no(context).setPropertyResolved(anyBoolean)
    }

    """set ELContext.propertyResolved to true and return "Class[String]" when called with a base of type Child and a property "f" """ in {
      resolver.getType(context, child, "f").getName mustEqual classOf[String].getName
      there was one(context).setPropertyResolved(true)
    }

//    """set ELContext.propertyResolved to true and return "Class[Array[String]]" when called with a base of type Child and a property "m" """ in {
//      skip("Not sure how to handle arrays!")
////      resolver.getValue(context, child, "m").asInstanceOf[Array[String]] must haveTheSameElementsAs Array("m")
////      there was one(context).setPropertyResolved(true)
//    }

    """set ELContext.propertyResolved to true and return "Class[String]" when called with a base of type Child and a property "f2" """ in {
      resolver.getType(context, child, "f2").getName mustEqual classOf[String].getName
      there was one(context).setPropertyResolved(true)
    }

    """set ELContext.propertyResolved to true and return "Class[String]" when called with a base of type Child and a property "m2" """ in {
      resolver.getType(context, child, "m2").getName mustEqual classOf[String].getName
      there was one(context).setPropertyResolved(true)
    }

    """set ELContext.propertyResolved to true and return "Class[Name]" when called with a base of type Child and a property "name" """ in {
      resolver.getType(context, child, "name").getName mustEqual classOf[Name].getName
      there was one(context).setPropertyResolved(true)
    }

    """set ELContext.propertyResolved to true and return "Class[Name]" when called with a base of type Child and a property "name2" """ in {
      resolver.getType(context, child, "name2").getName mustEqual classOf[Name].getName
      there was one(context).setPropertyResolved(true)
    }
  }

  "Calling setValue" should {

    val resolver = new ScalaELResolver
    val context = mock[ELContext]
    val child = new Child

    "leave ELContext.propertyResolved alone when called with a null base" in {
      resolver.setValue(context, null, "", "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

    "leave ELContext.propertyResolved alone when called with a base of type Child and a non-existing property" in {
      resolver.setValue(context, child, "", "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

//    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pf" """ in {
//      skip("Scala compiler magic: protected Scala members will result in public Java members (see javap Child)!")
//      resolver.setValue(context, child, "pf", "")
//      there was no(context).setPropertyResolved(anyBoolean)
//    }

    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pm" """ in {
      resolver.setValue(context, child, "pm", "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

//    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pf2" """ in {
//      skip("Scala compiler magic: protected Scala members will result in public Java members (see javap Child)!")
//      resolver.setValue(context, child, "pf2", "")
//      there was no(context).setPropertyResolved(anyBoolean)
//    }

    """leave ELContext.propertyResolved alone when called with a base of type Child and a property "pm2" """ in {
      resolver.setValue(context, child, "pm2", "")
      there was no(context).setPropertyResolved(anyBoolean)
    }

    """set ELContext.propertyResolved to true and set property to new value "new" when called with a base of type Child and a property "s2" """ in {
      resolver.setValue(context, child, "s2", "new")
      there was one(context).setPropertyResolved(true)
      child.s2 mustEqual "new"
    }

    """set ELContext.propertyResolved to true and set property to new value "Name(new, new)" when called with a base of type Child and a property "name" """ in {
      resolver.setValue(context, child, "name", Name("new", "new"))
      there was one(context).setPropertyResolved(true)
      child.name mustEqual Name("new", "new")
    }

    """set ELContext.propertyResolved to true and set property to new value "Name(new, new)" when called with a base of type Child and a property "name2" """ in {
      resolver.setValue(context, child, "name2", Name("new", "new"))
      there was one(context).setPropertyResolved(true)
      child.name2 mustEqual Name("new", "new")
    }
  }
}

class Parent {
  val f = "f"
  var name = Name("first", "last")
  def m = Array("m")
  protected val pf = "pf"
  private def pm = "pm"
}

class Child extends Parent {
  val f2 = "f2"
  var name2 = Name("first2", "last2")
  var s2 = "s2"
  def m2 = "m2"
  protected val pf2 = "pf2"
  private def pm2 = "pm2"
}

case class Name(first: String, last: String)
