/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ritika.nevatia
 */
@Entity
@Table(name = "URL_STORE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrlStore.findAll", query = "SELECT u FROM UrlStore u")
    , @NamedQuery(name = "UrlStore.findByUrlId", query = "SELECT u FROM UrlStore u WHERE u.urlId = :urlId")
    , @NamedQuery(name = "UrlStore.findByUrlLong", query = "SELECT u FROM UrlStore u WHERE u.urlLong = :urlLong")})
public class UrlStore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "URL_ID")
    private Integer urlId;
    @Size(max = 1024)
    @Column(name = "URL_LONG")
    private String urlLong;

    public UrlStore() {
    }

    public UrlStore(Integer urlId) {
        this.urlId = urlId;
    }

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public String getUrlLong() {
        return urlLong;
    }

    public void setUrlLong(String urlLong) {
        this.urlLong = urlLong;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (urlId != null ? urlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrlStore)) {
            return false;
        }
        UrlStore other = (UrlStore) object;
        if ((this.urlId == null && other.urlId != null) || (this.urlId != null && !this.urlId.equals(other.urlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.UrlStore[ urlId=" + urlId + " ]";
    }
    
}
