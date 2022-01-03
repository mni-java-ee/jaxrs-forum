CREATE OR REPLACE PACKAGE THREAD_PKG
    AS
    PROCEDURE pr_fetch_id_thread(p_id_thread integer, c_thread OUT SYS_REFCURSOR);

    PROCEDURE pr_fetch_all_thread(c_thread_all OUT SYS_REFCURSOR);

    FUNCTION fn_add_thread(p_thread_name varchar, p_thread_content varchar)
    return NUMBER;

    FUNCTION fn_update_thread(p_id_thread integer ,p_thread_name varchar, p_thread_content varchar)
    RETURN NUMBER;

    FUNCTION fn_delete_thread(p_id_thread integer)
    RETURN NUMBER;

END THREAD_pkg;
/