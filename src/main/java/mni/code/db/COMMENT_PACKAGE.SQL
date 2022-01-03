CREATE OR REPLACE PACKAGE COMMENT_PKG
AS
    PROCEDURE fetch_id_comment(fn_id_comment tbl_comment.ID_COMMENT%type, c_comment_id OUT SYS_REFCURSOR);

    PROCEDURE pc_fetch_all(c_comment_all OUT SYS_REFCURSOR);

    FUNCTION fn_add_comment(p_comments varchar, p_id_thread integer)
        RETURN number;

    FUNCTION fn_update_comment(p_id_comment integer, p_comments tbl_comment.comments%type)
        RETURN number;

    FUNCTION fn_delete_comment(p_id_comment integer)
        RETURN number;
END COMMENT_PKG;
/

SELECT * FROM TBL_THREAD;

DECLARE
    flag NUMBER;
BEGIN
    flag := comment_pkg.fn_update_comment(1,'Update pake function nih gan 2','12-12-2012');
END;
/

DECLARE

    flag number;

BEGIN
    flag := fn_add_comment(10,'test','12-12-2012',1);
END;
/
