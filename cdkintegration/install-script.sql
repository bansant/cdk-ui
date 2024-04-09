
DROP DATABASE IF EXISTS cdkbridge;
CREATE  Database cdkbridge;
use cdkbridge;

-- SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
--  SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

-- SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

DROP TABLE IF EXISTS `yac_boc_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_boc_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `company_id_fk` bigint(20) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_boc_master` (`boc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



DROP TABLE IF EXISTS `yac_store_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_store_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `office_id_fk` bigint(20) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `syncEnabled` tinyint(1) DEFAULT '1',
  `predictivePlannerEnabled` tinyint(1) DEFAULT '1',
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_sm_1` (`boc_id_fk`,`store_id`),
  KEY `fk_sm_sync_history_id` (`sync_history_id`),
  CONSTRAINT `fk_sm_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`)
  -- CONSTRAINT `fk_sm_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_customer_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_customer_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zip_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `home_phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cellular` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `business_phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `locale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `block_email` tinyint(1) DEFAULT '0',
  `block_mail` tinyint(1) DEFAULT '0',
  `block_phone` tinyint(1) DEFAULT '0',
  `block_sms` tinyint(1) NOT NULL DEFAULT '0',
  `record_source` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  `line_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `line_provider` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_cm_1` (`cust_no`,`boc_id`),
  KEY `fk_cm_boc_id_fk` (`boc_id_fk`),
  KEY `fk_cm_sync_history_id` (`sync_history_id`),
  CONSTRAINT `fk_cm_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`)
  -- CONSTRAINT `fk_cm_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1651234 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_employee_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_employee_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `name_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_em_1` (`emp_id`,`boc_id`,`store_id`),
  KEY `fk_em_boc_id_fk` (`boc_id_fk`),
  KEY `fk_em_store_id_fk` (`store_id_fk`),
  KEY `fk_em_sync_history_id` (`sync_history_id`),
  CONSTRAINT `fk_em_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_em_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
  -- CONSTRAINT `fk_em_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1285 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_inventory_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_inventory_vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `cust_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id_fk` bigint(20) NOT NULL,
  `vin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `make` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `model` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `year` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mileage` bigint(20) DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_iv_vl_boc_id_fk` (`boc_id_fk`),
  KEY `fk_iv_vl_customer_id_fk` (`customer_id_fk`),
  KEY `fk_iv_vl_store_id_fk` (`store_id_fk`),
  KEY `fk_iv_sync_history_id` (`sync_history_id`),
  -- CONSTRAINT `fk_iv_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`),
  CONSTRAINT `fk_iv_vl_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_iv_vl_customer_id_fk` FOREIGN KEY (`customer_id_fk`) REFERENCES `yac_customer_master` (`id`),
  CONSTRAINT `fk_iv_vl_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=367796 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_op_code_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_op_code_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_code` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `op_code_description` longtext COLLATE utf8mb4_unicode_ci,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) DEFAULT NULL,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jocm_store_id_fk` (`store_id_fk`),
  KEY `fk_opcm_sync_history_id` (`sync_history_id`),
  CONSTRAINT `fk_jocm_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
  -- CONSTRAINT `fk_opcm_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=681797 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `yac_service_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_service_vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `cust_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id_fk` bigint(20) NOT NULL,
  `vin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `make` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `model` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `year` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mileage` bigint(20) DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_service_vl_1` (`boc_id`,`cust_no`,`vin`),
  KEY `fk_service_vl_boc_id_fk` (`boc_id_fk`),
  KEY `fk_service_vl_customer_id_fk` (`customer_id_fk`),
  KEY `fk_sv_sync_history_id` (`sync_history_id`),
  CONSTRAINT `fk_service_vl_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_service_vl_customer_id_fk` FOREIGN KEY (`customer_id_fk`) REFERENCES `yac_customer_master` (`id`)
  -- CONSTRAINT `fk_sv_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002379 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_sales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `cust_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id_fk` bigint(20) NOT NULL,
  `deal_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deal_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sold_date` timestamp NULL DEFAULT NULL,
  `vin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `inventory_vehicle_id_fk` bigint(20) NOT NULL,
  `salesman` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fi_manager` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sls_manager` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sale_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trade_in_flag` tinyint(1) DEFAULT '0',
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  `when_added_title` timestamp NULL DEFAULT NULL,
  `dealevent5date` timestamp NULL DEFAULT NULL,
  `dealevent5` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `recordAddedViaWhenAddedTitleSync` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_sales_1` (`boc_id`,`cust_no`,`inventory_vehicle_id_fk`,`deal_number`),
  KEY `fk_sales_boc_id_fk` (`boc_id_fk`),
  KEY `fk_sales_customer_id_fk` (`customer_id_fk`),
  KEY `fk_sales_inventory_vehicle_id_fk` (`inventory_vehicle_id_fk`),
  KEY `fk_sales_store_id_fk` (`store_id_fk`),
  KEY `fk_s_sync_history_id` (`sync_history_id`),
  -- CONSTRAINT `fk_s_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`),
  CONSTRAINT `fk_sales_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_sales_customer_id_fk` FOREIGN KEY (`customer_id_fk`) REFERENCES `yac_customer_master` (`id`),
  CONSTRAINT `fk_sales_inventory_vehicle_id_fk` FOREIGN KEY (`inventory_vehicle_id_fk`) REFERENCES `yac_inventory_vehicle` (`id`),
  CONSTRAINT `fk_sales_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104590 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `cust_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id_fk` bigint(20) NOT NULL,
  `ro_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `advisor_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `service_vehicle_id_fk` bigint(20) NOT NULL,
  `opened_date` timestamp NULL DEFAULT NULL,
  `close_date` timestamp NULL DEFAULT NULL,
  `rosalecp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rosaleip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rosalewp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `mileage` bigint(20) DEFAULT NULL,
  `record_source_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'DMS',
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_service_1` (`boc_id`,`store_id_fk`,`customer_id_fk`,`service_vehicle_id_fk`,`ro_number`),
  KEY `fk_service_boc_id_fk` (`boc_id_fk`),
  KEY `fk_service_customer_id_fk` (`customer_id_fk`),
  KEY `fk_service_srv_vehicle_id_fk` (`service_vehicle_id_fk`),
  KEY `fk_service_store_id_fk` (`store_id_fk`),
  KEY `fk_serv_sync_history_id` (`sync_history_id`),
  -- CONSTRAINT `fk_serv_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`),
  CONSTRAINT `fk_service_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_service_customer_id_fk` FOREIGN KEY (`customer_id_fk`) REFERENCES `yac_customer_master` (`id`),
  CONSTRAINT `fk_service_srv_vehicle_id_fk` FOREIGN KEY (`service_vehicle_id_fk`) REFERENCES `yac_service_vehicle` (`id`),
  CONSTRAINT `fk_service_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=814491 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_service_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_service_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `ro_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `service_id_fk` bigint(20) NOT NULL,
  `op_code_master_id_fk` bigint(20) NOT NULL,
  `lop_seq_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `actual_hours` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sold_hours` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_cost` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_sale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `misc_cost` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `misc_sale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parts_cost` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_technician` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parts_sale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `closed` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_serviced_1` (`boc_id_fk`,`store_id_fk`,`service_id_fk`,`op_code_master_id_fk`,`lop_seq_no`),
  KEY `fk_serviced_store_id_fk` (`store_id_fk`),
  KEY `fk_serviced_service_id_fk` (`service_id_fk`),
  KEY `fk_serviced_op_code_master_id_fk` (`op_code_master_id_fk`),
  KEY `fk_sd_sync_history_id` (`sync_history_id`),
  -- CONSTRAINT `fk_sd_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`),
  CONSTRAINT `fk_serviced_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_serviced_op_code_master_id_fk` FOREIGN KEY (`op_code_master_id_fk`) REFERENCES `yac_op_code_master` (`id`),
  CONSTRAINT `fk_serviced_service_id_fk` FOREIGN KEY (`service_id_fk`) REFERENCES `yac_service` (`id`),
  CONSTRAINT `fk_serviced_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2382529 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_open_ro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_open_ro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `cust_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id_fk` bigint(20) NOT NULL,
  `ro_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `advisor_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `open_ro_vehicle_id_fk` bigint(20) NOT NULL,
  `opened_date` timestamp NULL DEFAULT NULL,
  `close_date` timestamp NULL DEFAULT NULL,
  `rosalecp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rosaleip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rosalewp` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status_desc` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `mileage` bigint(20) DEFAULT NULL,
  `record_source_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'DMS',
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_open_ro_1` (`boc_id`,`store_id_fk`,`customer_id_fk`,`open_ro_vehicle_id_fk`,`ro_number`),
  KEY `fk_open_ro_boc_id_fk` (`boc_id_fk`),
  KEY `fk_open_ro_customer_id_fk` (`customer_id_fk`),
  KEY `fk_open_ro_srv_vehicle_id_fk` (`open_ro_vehicle_id_fk`),
  KEY `fk_open_ro_store_id_fk` (`store_id_fk`),
  KEY `fk_open_ro_sync_history_id` (`sync_history_id`),
  -- CONSTRAINT `fk_open_ro_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`),
  CONSTRAINT `fk_open_ro_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_open_ro_customer_id_fk` FOREIGN KEY (`customer_id_fk`) REFERENCES `yac_customer_master` (`id`),
  CONSTRAINT `fk_open_ro_srv_vehicle_id_fk` FOREIGN KEY (`open_ro_vehicle_id_fk`) REFERENCES `yac_service_vehicle` (`id`),
  CONSTRAINT `fk_open_ro_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=814491 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_open_ro_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_open_ro_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boc_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `boc_id_fk` bigint(20) NOT NULL,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `ro_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `open_ro_id_fk` bigint(20) NOT NULL,
  `op_code_master_id_fk` bigint(20) NOT NULL,
  `lop_seq_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `actual_hours` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sold_hours` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_cost` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_sale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `misc_cost` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `misc_sale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parts_cost` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parts_sale` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `line_estimated_hours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `line_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `part_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `part_desc` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `labor_technician` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `closed` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_open_ro_detail_1` (`boc_id_fk`,`store_id_fk`,`open_ro_id_fk`,`op_code_master_id_fk`,`lop_seq_no`),
  KEY `fk_open_ro_detail_store_id_fk` (`store_id_fk`),
  KEY `fk_open_ro_detail_service_id_fk` (`open_ro_id_fk`),
  KEY `fk_open_ro_detail_op_code_master_id_fk` (`op_code_master_id_fk`),
  KEY `fk_open_ro_detail_sync_history_id` (`sync_history_id`),
  -- CONSTRAINT `fk_open_ro_detail_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`),
  CONSTRAINT `fk_open_ro_detail_boc_id_fk` FOREIGN KEY (`boc_id_fk`) REFERENCES `yac_boc_master` (`id`),
  CONSTRAINT `fk_open_ro_detail_op_code_master_id_fk` FOREIGN KEY (`op_code_master_id_fk`) REFERENCES `yac_op_code_master` (`id`),
  CONSTRAINT `fk_open_ro_detail_service_id_fk` FOREIGN KEY (`open_ro_id_fk`) REFERENCES `yac_open_ro` (`id`),
  CONSTRAINT `fk_open_ro_detail_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2382529 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `yac_open_ro_status_trail`;

CREATE TABLE `yac_open_ro_status_trail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ro_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prev_status_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `current_status_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2382529 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `yac_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yac_appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_id_fk` bigint(20) NOT NULL,
  `cust_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id_fk` bigint(20) NOT NULL,
  `appointment_id` bigint(20) NOT NULL,
  `appointment_date` varchar(255) DEFAULT NULL,
  `appointment_time` varchar(255) DEFAULT NULL,
  `promised_date` varchar(255) DEFAULT NULL,
  `promised_time` varchar(255) DEFAULT NULL,
  
  `service_vehicle_id_fk` bigint(20) NOT NULL,
  `vin` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedOn` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  
  `record_source_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'DMS',
  `sync_history_id` bigint(20) DEFAULT NULL,
  `when_added` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_appointment_1` (`store_id_fk`,`customer_id_fk`,`service_vehicle_id_fk`,`appointment_id`),
  KEY `fk_appointment_customer_id_fk` (`customer_id_fk`),
  KEY `fk_appointment_srv_vehicle_id_fk` (`service_vehicle_id_fk`),
  KEY `fk_appointment_store_id_fk` (`store_id_fk`),
  KEY `fk_appointment_sync_history_id` (`sync_history_id`),
  -- CONSTRAINT `fk_serv_sync_history_id` FOREIGN KEY (`sync_history_id`) REFERENCES `yac_sync_history` (`id`),
  CONSTRAINT `fk_appointment_customer_id_fk` FOREIGN KEY (`customer_id_fk`) REFERENCES `yac_customer_master` (`id`),
  CONSTRAINT `fk_appointment_srv_vehicle_id_fk` FOREIGN KEY (`service_vehicle_id_fk`) REFERENCES `yac_service_vehicle` (`id`),
  CONSTRAINT `fk_appointment_store_id_fk` FOREIGN KEY (`store_id_fk`) REFERENCES `yac_store_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=814491 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS CRON_FIRED_TRIGGERS;

DROP TABLE IF EXISTS CRON_PAUSED_TRIGGER_GRPS;

DROP TABLE IF EXISTS CRON_SCHEDULER_STATE;

DROP TABLE IF EXISTS CRON_LOCKS;

DROP TABLE IF EXISTS CRON_SIMPLE_TRIGGERS;

DROP TABLE IF EXISTS CRON_SIMPROP_TRIGGERS;

DROP TABLE IF EXISTS CRON_CRON_TRIGGERS;

DROP TABLE IF EXISTS CRON_BLOB_TRIGGERS;

DROP TABLE IF EXISTS CRON_TRIGGERS;

DROP TABLE IF EXISTS CRON_JOB_DETAILS;

DROP TABLE IF EXISTS CRON_CALENDARS;

CREATE TABLE CRON_JOB_DETAILS(

SCHED_NAME VARCHAR(120) NOT NULL,

JOB_NAME VARCHAR(190) NOT NULL,

JOB_GROUP VARCHAR(190) NOT NULL,

DESCRIPTION VARCHAR(250) NULL,

JOB_CLASS_NAME VARCHAR(250) NOT NULL,

IS_DURABLE VARCHAR(1) NOT NULL,

IS_NONCONCURRENT VARCHAR(1) NOT NULL,

IS_UPDATE_DATA VARCHAR(1) NOT NULL,

REQUESTS_RECOVERY VARCHAR(1) NOT NULL,

JOB_DATA BLOB NULL,

PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))

ENGINE=InnoDB;

CREATE TABLE CRON_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
JOB_NAME VARCHAR(190) NOT NULL,
JOB_GROUP VARCHAR(190) NOT NULL,
DESCRIPTION VARCHAR(250) NULL,
NEXT_FIRE_TIME BIGINT(13) NULL,
PREV_FIRE_TIME BIGINT(13) NULL,
PRIORITY INTEGER NULL,
TRIGGER_STATE VARCHAR(16) NOT NULL,
TRIGGER_TYPE VARCHAR(8) NOT NULL,
START_TIME BIGINT(13) NOT NULL,
END_TIME BIGINT(13) NULL,
CALENDAR_NAME VARCHAR(190) NULL,
MISFIRE_INSTR SMALLINT(2) NULL,
JOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
REFERENCES CRON_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))

ENGINE=InnoDB;

CREATE TABLE CRON_SIMPLE_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
REPEAT_COUNT BIGINT(7) NOT NULL,
REPEAT_INTERVAL BIGINT(12) NOT NULL,
TIMES_TRIGGERED BIGINT(10) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES CRON_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))

ENGINE=InnoDB;

CREATE TABLE CRON_CRON_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
CRON_EXPRESSION VARCHAR(120) NOT NULL,
TIME_ZONE_ID VARCHAR(80),
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES CRON_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))

ENGINE=InnoDB;

CREATE TABLE CRON_SIMPROP_TRIGGERS(
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
STR_PROP_1 VARCHAR(512) NULL,
STR_PROP_2 VARCHAR(512) NULL,
STR_PROP_3 VARCHAR(512) NULL,
INT_PROP_1 INT NULL,
INT_PROP_2 INT NULL,
LONG_PROP_1 BIGINT NULL,
LONG_PROP_2 BIGINT NULL,
DEC_PROP_1 NUMERIC(13,4) NULL,
DEC_PROP_2 NUMERIC(13,4) NULL,
BOOL_PROP_1 VARCHAR(1) NULL,
BOOL_PROP_2 VARCHAR(1) NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES CRON_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))

ENGINE=InnoDB;

CREATE TABLE CRON_BLOB_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
BLOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES CRON_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))

ENGINE=InnoDB;

CREATE TABLE CRON_CALENDARS (
SCHED_NAME VARCHAR(120) NOT NULL,
CALENDAR_NAME VARCHAR(190) NOT NULL,
CALENDAR BLOB NOT NULL,
PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))

ENGINE=InnoDB;

CREATE TABLE CRON_PAUSED_TRIGGER_GRPS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))

ENGINE=InnoDB;

CREATE TABLE CRON_FIRED_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
ENTRY_ID VARCHAR(95) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
INSTANCE_NAME VARCHAR(190) NOT NULL,
FIRED_TIME BIGINT(13) NOT NULL,
SCHED_TIME BIGINT(13) NOT NULL,
PRIORITY INTEGER NOT NULL,
STATE VARCHAR(16) NOT NULL,
JOB_NAME VARCHAR(190) NULL,
JOB_GROUP VARCHAR(190) NULL,
IS_NONCONCURRENT VARCHAR(1) NULL,
REQUESTS_RECOVERY VARCHAR(1) NULL,
PRIMARY KEY (SCHED_NAME,ENTRY_ID))

ENGINE=InnoDB;

CREATE TABLE CRON_SCHEDULER_STATE (SCHED_NAME VARCHAR(120) NOT NULL,
INSTANCE_NAME VARCHAR(190) NOT NULL,
LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
CHECKIN_INTERVAL BIGINT(13) NOT NULL,
PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))

ENGINE=InnoDB;

CREATE TABLE CRON_LOCKS (SCHED_NAME VARCHAR(120) NOT NULL,
LOCK_NAME VARCHAR(40) NOT NULL,
PRIMARY KEY (SCHED_NAME,LOCK_NAME))

ENGINE=InnoDB;

CREATE INDEX IDX_CRON_J_REQ_RECOVERY ON CRON_JOB_DETAILS(SCHED_NAME,
REQUESTS_RECOVERY);

CREATE INDEX IDX_CRON_J_GRP ON CRON_JOB_DETAILS(SCHED_NAME,JOB_GROUP);

CREATE INDEX IDX_CRON_T_J ON CRON_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);

CREATE INDEX IDX_CRON_T_JG ON CRON_TRIGGERS(SCHED_NAME,JOB_GROUP);

CREATE INDEX IDX_CRON_T_C ON CRON_TRIGGERS(SCHED_NAME,CALENDAR_NAME);

CREATE INDEX IDX_CRON_T_G ON CRON_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);

CREATE INDEX IDX_CRON_T_STATE ON CRON_TRIGGERS(SCHED_NAME,TRIGGER_STATE);

CREATE INDEX IDX_CRON_T_N_STATE ON CRON_TRIGGERS(SCHED_NAME,TRIGGER_NAME,
TRIGGER_GROUP,TRIGGER_STATE);

CREATE INDEX IDX_CRON_T_N_G_STATE ON CRON_TRIGGERS(SCHED_NAME,
TRIGGER_GROUP, TRIGGER_STATE);

CREATE INDEX IDX_CRON_T_NEXT_FIRE_TIME ON CRON_TRIGGERS(SCHED_NAME,
NEXT_FIRE_TIME);

CREATE INDEX IDX_CRON_T_NFT_ST ON CRON_TRIGGERS(SCHED_NAME,
TRIGGER_STATE,NEXT_FIRE_TIME);

CREATE INDEX IDX_CRON_T_NFT_MISFIRE ON CRON_TRIGGERS(SCHED_NAME,
MISFIRE_INSTR,NEXT_FIRE_TIME);

CREATE INDEX IDX_CRON_T_NFT_ST_MISFIRE ON CRON_TRIGGERS(SCHED_NAME,
MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);

CREATE INDEX IDX_CRON_T_NFT_ST_MISFIRE_GRP ON CRON_TRIGGERS(
SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

CREATE INDEX IDX_CRON_FT_TRIG_INST_NAME ON CRON_FIRED_TRIGGERS(
SCHED_NAME,INSTANCE_NAME);

CREATE INDEX IDX_CRON_FT_INST_JOB_REQ_RCVRY ON CRON_FIRED_TRIGGERS(
SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);

CREATE INDEX IDX_CRON_FT_J_G ON CRON_FIRED_TRIGGERS(SCHED_NAME,
JOB_NAME,JOB_GROUP);

CREATE INDEX IDX_CRON_FT_JG ON CRON_FIRED_TRIGGERS(SCHED_NAME,
JOB_GROUP);

CREATE INDEX IDX_CRON_FT_T_G ON CRON_FIRED_TRIGGERS(SCHED_NAME,
TRIGGER_NAME,TRIGGER_GROUP);

CREATE INDEX IDX_CRON_FT_TG ON CRON_FIRED_TRIGGERS(SCHED_NAME,
TRIGGER_GROUP);

commit;

-- SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
