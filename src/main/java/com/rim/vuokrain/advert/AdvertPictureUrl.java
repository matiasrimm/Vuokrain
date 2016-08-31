package com.rim.vuokrain.advert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pictureurls")
public class AdvertPictureUrl {

		private long id;
		private long advertid;
		private String url;		
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		public long getAdvertid() {
			return advertid;
		}
		public void setAdvertid(long advertid) {
			this.advertid = advertid;
		}
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
}
