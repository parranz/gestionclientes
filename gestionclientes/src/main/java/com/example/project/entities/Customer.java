package com.example.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Customer implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String firstname;

   @Column
   private String lastname;

   @OneToOne
   private Address address;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((Customer) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getFirstname()
   {
      return this.firstname;
   }

   public void setFirstname(final String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return this.lastname;
   }

   public void setLastname(final String lastname)
   {
      this.lastname = lastname;
   }

   public Address getAddress()
   {
      return this.address;
   }

   public void setAddress(final Address address)
   {
      this.address = address;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (firstname != null && !firstname.trim().isEmpty())
         result += "firstname: " + firstname;
      if (lastname != null && !lastname.trim().isEmpty())
         result += ", lastname: " + lastname;
      return result;
   }
}