CREATE OR REPLACE PACKAGE BODY Comment_pkg
AS
    PROCEDURE fetch_id_comment(
        fn_id_comment tbl_comment.ID_COMMENT%type,
        c_comment_id OUT SYS_REFCURSOR
    )
        IS
        fn_comments tbl_comment.comments%TYPE;
        fn_comment_date tbl_comment.comment_date%TYPE;
        fn_id_thread tbl_comment.id_comment%TYPE;
        fn_id_tbthread tbl_thread.id_thread%TYPE;

    BEGIN
        OPEN c_comment_id FOR
            SELECT id_comment, comments, comment_date, id_thread
            FROM tbl_comment
            WHERE id_comment = fn_id_comment;
    END fetch_id_comment;


    PROCEDURE pc_fetch_all(c_comment_all OUT SYS_REFCURSOR) AS
    BEGIN
        OPEN c_comment_all FOR
            SELECT  id_comment, comments, comment_date, id_thread FROM tbl_comment ORDER BY id_comment ASC;
    END pc_fetch_all;

    FUNCTION fn_add_comment(p_comments varchar, p_id_thread integer)
        RETURN number IS
        type flag is table of number;
        bulkId flag;
    BEGIN
        INSERT INTO tbl_comment (ID_COMMENT , COMMENTS , COMMENT_DATE, ID_THREAD) VALUES(ID_CMNT.nextval, p_comments, SYSDATE, p_id_thread) returning id_comment bulk collect into bulkId;
        COMMIT;
        RETURN bulkId.count;
    END;

    FUNCTION fn_update_comment(p_id_comment integer, p_comments tbl_comment.comments%type)
        RETURN NUMBER IS flag number;
    BEGIN
        UPDATE tbl_comment
        SET comments = p_comments, comment_date = SYSDATE
        WHERE id_comment = p_id_comment;
        RETURN (flag);
    END;

    FUNCTION fn_delete_comment(p_id_comment integer)
        RETURN NUMBER IS flag number;
    BEGIN
        DELETE FROM tbl_comment
        WHERE id_comment = p_id_comment;

        RETURN (flag);
    END;
END Comment_pkg;
/



CREATE OR REPLACE FUNCTION fn_add_comment(p_id_comment integer, p_comments varchar, p_comment_date varchar, p_id_thread integer)
    RETURN number IS
    type flag is table of number;
    bulkId flag;
BEGIN
    INSERT INTO tbl_comment (ID_COMMENT , COMMENTS , COMMENT_DATE, ID_THREAD) VALUES(p_id_comment, p_comments, p_comment_date, p_id_thread) returning id_comment bulk collect into bulkId;
    COMMIT;
    RETURN bulkId.count;
END;
/