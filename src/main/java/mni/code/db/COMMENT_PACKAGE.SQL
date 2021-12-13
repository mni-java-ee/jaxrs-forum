--  INSERT COMMENT
CREATE PACKAGE comment_pckg
    AS
    PROCEDURE add_comments(
    fn_id_comment tbl_comment.ID_COMMENT%type,
    fn_comments tbl_comment.COMMENTS%type,
    fn_comment_date tbl_comment.COMMENT_DATE%type,
    fn_id_thread tbl_comment.ID_THREAD%type
);

-- SELECT ALL COMMENT
PROCEDURE list_comment;

-- SELECT COMMENT BY ID
PROCEDURE fetch_id_comment(
    fn_id_comment tbl_comment.ID_COMMENT%type
);

-- UPDATE COMMENT
PROCEDURE update_comment(
    fn_comments tbl_comment.COMMENTS%type,
    fn_comment_date tbl_comment.COMMENT_DATE%type,
    fn_id_comment tbl_comment.ID_COMMENT%type
);

-- DELETE COMMENT
PROCEDURE delete_comment(
    fn_id_comment tbl_comment.ID_COMMENT%type
);
 END comment_pckg;
/