package com.example.project.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.persistence.OneToOne;
import com.example.project.entities.Customer;

@Entity
public class Address implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String street;

   @Column
   private int number;

   @Column
   private String province;

   @OneToOne
   private Customer customer;

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
         return id.equals(((Address) that).id);
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

   public String getStreet()
   {
      return this.street;
   }

   public void setStreet(final String street)
   {
      this.street = street;
   }

   public int getNumber()
   {
      return this.number;
   }

   public void setNumber(final int number)
   {
      this.number = number;
   }

   public String getProvince()
   {
      return this.province;
   }

   public void setProvince(final String province)
   {
      this.province = province;
   }

   public Customer getCustomer()
   {
      return this.customer;
   }

   public void setCustomer(final Customer customer)
   {
      this.customer = customer;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (street != null && !street.trim().isEmpty())
         result += "street: " + street;
      result += ", number: " + number;
      if (province != null && !province.trim().isEmpty())
         result += ", province: " + province;
      return result;
   }
}