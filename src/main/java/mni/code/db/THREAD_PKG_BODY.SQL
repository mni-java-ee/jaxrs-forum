CREATE OR REPLACE PACKAGE BODY THREAD_PKG
    AS
    PROCEDURE pr_fetch_id_thread(p_id_thread integer, c_thread OUT SYS_REFCURSOR)
        IS
BEGIN
OPEN c_thread FOR
SELECT id_thread, thread_name, thread_date, thread_content
FROM TBL_THREAD
WHERE id_thread = p_id_thread;

END pr_fetch_id_thread;

    PROCEDURE pr_fetch_all_thread(c_thread_all OUT SYS_REFCURSOR)
IS
BEGIN
OPEN c_thread_all FOR
SELECT * FROM TBL_THREAD ORDER BY id_thread ASC;

END pr_fetch_all_thread;

    FUNCTION fn_add_thread(p_thread_name varchar, p_thread_content varchar)
    RETURN number IS
        type flag is table of number;
        bulkId flag;
BEGIN
INSERT INTO TBL_THREAD (ID_THREAD , THREAD_NAME , THREAD_DATE, THREAD_CONTENT) VALUES(ID_THRD.nextval, p_thread_name, SYSDATE, p_thread_content) returning ID_THREAD bulk collect into bulkId;
COMMIT;
RETURN bulkId.count;
END;

    FUNCTION fn_update_thread(p_id_thread integer, p_thread_name varchar, p_thread_content varchar)
    RETURN NUMBER IS flag number;
BEGIN
UPDATE TBL_THREAD
SET THREAD_NAME = p_thread_name, THREAD_DATE = SYSDATE, THREAD_CONTENT = p_thread_content
WHERE ID_THREAD = p_id_thread;
RETURN (flag);
END;

    FUNCTION fn_delete_thread(p_id_thread integer)
    RETURN NUMBER IS flag number;
BEGIN
DELETE FROM TBL_THREAD
WHERE ID_THREAD = p_id_thread;
RETURN (flag);
END;

END THREAD_PKG;
/