
CREATE TABLE world.MODULE_tbl (
  ID INT NOT NULL AUTO_INCREMENT,
  MODULE varchar(100),
  MAJOR_TYPE varchar(100),
  MAIN_TYPE varchar(100),
  FORM_OUTPUT varchar(100),
  created_date datetime(6),
    primary key(id));
        
CREATE TABLE world.MODULE_LOG (
  ID INT NOT NULL AUTO_INCREMENT,
  MODULE varchar(100),
  MAJOR_TYPE varchar(100),
  MAIN_TYPE varchar(100),
  FORM_OUTPUT varchar(100),
  ACTION_LOG varchar(100),
  created_date datetime(6),
    primary key(id));
    
    
CREATE TRIGGER world.module_insert_audit_trigger 
    AFTER INSERT ON world.MODULE_tbl
    FOR EACH ROW 
 INSERT INTO world.MODULE_LOG
 SET ID = NEW.ID,
     MODULE = NEW.MODULE,
     MAJOR_TYPE = NEW.MAJOR_TYPE,
     FORM_OUTPUT = NEW.FORM_OUTPUT,
     ACTION_LOG = 'INSERT',
     created_date = NOW();
     
CREATE TRIGGER world.module_update_audit_trigger 
	BEFORE UPDATE ON world.MODULE_tbl 
    FOR EACH ROW 
INSERT INTO world.MODULE_LOG
 SET ID = OLD.ID,
     MODULE = OLD.MODULE,
     MAJOR_TYPE = OLD.MAJOR_TYPE,
     MAIN_TYPE = OLD.MAIN_TYPE,
     FORM_OUTPUT = OLD.FORM_OUTPUT,
     ACTION_LOG = 'UPDATE',
     created_date = NOW();
          
CREATE TRIGGER world.module_delete_audit_trigger 
    BEFORE DELETE ON world.MODULE_tbl
    FOR EACH ROW 
 INSERT INTO world.MODULE_LOG
 SET ID = OLD.ID,
     MODULE = OLD.MODULE,
     MAJOR_TYPE = OLD.MAJOR_TYPE,
     FORM_OUTPUT = OLD.FORM_OUTPUT,
     ACTION_LOG = 'DELETE',
     created_date = NOW();