CREATE TABLE publisher
(
  id             SERIAL                 NOT NULL,
  canonical_name CHARACTER VARYING(255) NOT NULL,
  title          CHARACTER VARYING(255) NOT NULL,
  url            CHARACTER VARYING(255) NOT NULL,
  description    CHARACTER VARYING(1024),
  active         BOOLEAN                NOT NULL DEFAULT TRUE,
  CONSTRAINT publisher_pkey PRIMARY KEY (id),
  CONSTRAINT publisher_canonical_name_uidx UNIQUE (canonical_name)
);

CREATE TABLE rss_feed
(
  id                 SERIAL                 NOT NULL,
  publisher_id       INTEGER                NOT NULL,
  url                CHARACTER VARYING(255) NOT NULL,
  name               CHARACTER VARYING(255) NOT NULL,
  link               CHARACTER VARYING(255) NOT NULL,
  description        CHARACTER VARYING(1024),
  author             CHARACTER VARYING(255),
  copyright          CHARACTER VARYING(255),
  image_url          CHARACTER VARYING(255),
  last_check         TIMESTAMP WITHOUT TIME ZONE,
  planned_check      TIMESTAMP WITHOUT TIME ZONE,
  in_progress_since  TIMESTAMP WITHOUT TIME ZONE,
  http_last_etag     CHARACTER VARYING(255),
  http_last_modified TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT rss_feed_pkey PRIMARY KEY (id),
  CONSTRAINT rss_feed_publisher_id_fkey FOREIGN KEY (publisher_id) REFERENCES publisher (id) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT rss_feed_url_uidx UNIQUE (url)
);

CREATE TABLE rss_item
(
  id                        SERIAL                      NOT NULL,
  uid                       CHARACTER(11)               NOT NULL,
  publisher_id              INTEGER                     NOT NULL,
  rss_feed_id               INTEGER                     NOT NULL,
  guid_hash                 BYTEA                       NOT NULL,
  content_hash              BYTEA                       NOT NULL,
  title                     CHARACTER VARYING(255)      NOT NULL,
  author                    CHARACTER VARYING(255),
  link                      CHARACTER VARYING(255)      NOT NULL,
  guid                      CHARACTER VARYING(255)      NOT NULL,
  publication_date          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  adjusted_publication_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  created_date              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  description               CHARACTER VARYING(8192),
  CONSTRAINT rss_item_pkey PRIMARY KEY (id),
  CONSTRAINT rss_item_publisher_id_fkey FOREIGN KEY (publisher_id) REFERENCES publisher (id) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT rss_item_rss_feed_id_fkey FOREIGN KEY (rss_feed_id) REFERENCES rss_feed (id) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT rss_item_uid_uidx_1 UNIQUE (uid),
  CONSTRAINT rss_item_uid_uidx_2 UNIQUE (publisher_id, guid_hash),
  CONSTRAINT rss_item_uid_uidx_3 UNIQUE (publisher_id, content_hash)
);