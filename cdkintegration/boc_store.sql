INSERT INTO cdkbridge.yac_boc_master
(boc_id, boc_name, createdOn, updatedOn, company_id_fk, archived)
VALUES('1', 'CdkBoc', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0);


INSERT INTO cdkbridge.yac_store_master
(boc_id, boc_id_fk, store_id, store_name, createdOn, updatedOn, sync_history_id, office_id_fk, archived, syncEnabled, predictivePlannerEnabled, when_added)
VALUES('1', 68, '3PADEV1', 'Yark Auto Group – Dev', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0, 0, 1, 1, NULL);

INSERT INTO cdkbridge.yac_store_master
(boc_id, boc_id_fk, store_id, store_name, createdOn, updatedOn, sync_history_id, office_id_fk, archived, syncEnabled, predictivePlannerEnabled, when_added)
VALUES('1', 68, '3PAAIMACHINESLIVE1', 'Yark Auto Group – Jeep', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0, 0, 1, 1, NULL);

INSERT INTO cdkbridge.yac_store_master
(boc_id, boc_id_fk, store_id, store_name, createdOn, updatedOn, sync_history_id, office_id_fk, archived, syncEnabled, predictivePlannerEnabled, when_added)
VALUES('1', 68, '3PAAIMACHINESLIVE2', 'Yark Auto Group – BMW', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0, 0, 1, 1, NULL);


