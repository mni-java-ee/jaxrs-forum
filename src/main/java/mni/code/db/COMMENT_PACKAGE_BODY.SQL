CREATE OR REPLACE PACKAGE BODY COMMENT_PACKAGE
    AS
-- INSERT COMMENT
    PROCEDURE add_comments(
        fn_id_comment TBL_COMMENT.ID_COMMENT%type,
        fn_comments TBL_COMMENT.COMMENTS%type,
        fn_comment_data TBL_COMMENT.COMMENT_DATE%type,
        fn_id_thread TBL_COMMENT.ID_THREAD%type)
    IS
        BEGIN
            INSERT INTO TBL_COMMENT (ID_COMMENT , COMMENTS ,COMMENT_DATE, ID_THREAD)
                VALUES(fn_id_comment, fn_comments, fn_comment_data , fn_id_thread);
        END add_comments;

-- SELECT ALL COMMENT
        PROCEDURE list_comment IS;
    END;