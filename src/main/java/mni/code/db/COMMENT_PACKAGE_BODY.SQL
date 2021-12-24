CREATE PACKAGE BODY COMMENT_PCKG
    AS
-- INSERT COMMENT
    PROCEDURE add_comments(
        fn_id_comment TBL_COMMENT.ID_COMMENT%type,
        fn_comments TBL_COMMENT.COMMENTS%type,
        fn_comment_date TBL_COMMENT.COMMENT_DATE%type,
        fn_id_thread TBL_COMMENT.ID_THREAD%type)
    IS
        BEGIN
            INSERT INTO TBL_COMMENT (ID_COMMENT , COMMENTS ,COMMENT_DATE, ID_THREAD)
                VALUES(fn_id_comment, fn_comments, fn_comment_date , fn_id_thread);
        END add_comments;

/*-- SELECT COMMENT BY ID
        PROCEDURE fetch_id_comment(fn_id_comment TBL_COMMENT.ID_COMMENT%type) IS
            fn_comments TBL_COMMENT.COMMENTS%type;
            fn_comment_date TBL_COMMENT.COMMENT_DATE%type;
            fn_id_thread TBL_THREAD.ID_THREAD%type;

    BEGIN
        Select  c.comments , c.comment_date , c.id_comment , t.id_thread
        into fn_comments , fn_comment_date  , fn_id_thread
        From tbl_comment c left join tbl_thread t on c.id_thread = t.id_thread
        where c.id_comment = fn_id_comment;
            dbms_output.put_line('---------------Get Comment By ID--------------------');
            dbms_output.put_line('Id_Comment              : ' ||fn_id_comment);
            dbms_output.put_line('Comment              : ' ||fn_comments);
            dbms_output.put_line('Comment Date              : ' ||fn_comment_date);
            dbms_output.put_line('Id_Thread              : ' ||fn_id_thread);

            end fetch_id_comment;


-- SELECT ALL COMMENT
PROCEDURE list_comment IS
    fn_id_comment tbl_comment.ID_COMMENT%type;
    fn_comments tbl_comment.COMMENTS%type;
    fn_comment_date tbl_comment.COMMENT_DATE%type;
    fn_id_thread TBL_THREAD.ID_THREAD%type;
    fn_thread_content TBL_THREAD.THREAD_CONTENT%type;
    fn_thread_name TBL_THREAD.THREAD_NAME%type;
    CURSOR c_comment is
        select DISTINCT t.id_thread , t.thread_content , t.thread_name ,  c.id_comment   , c.comments , c.comment_date
        from tbl_thread t left join tbl_comment c on t.id_thread = c.id_thread where c.id_comment is not null order by t.id_thread , c.id_comment  ASC;
BEGIN
    open c_comment; --cursor for allocating the memory
    loop
        FETCH c_comment into fn_id_comment, fn_comments, fn_comment_date, fn_id_thread , fn_thread_content, fn_thread_name ; --cursor for retrieving the data
        EXIT WHEN c_comment%notfound;
        dbms_output.put_line(fn_id_thread || ' ' || fn_thread_content || ' ' || fn_thread_name || ' ' || fn_id_comment || ' ' || fn_comments || ' ' || fn_comment_date);
    END LOOP;
    CLOSE c_comment; --cursor to release the allocated memory
END list_comment;

-- UPDATE COMMENT
    PROCEDURE update_comment(
        fn_id_comment TBL_COMMENT.ID_COMMENT%type,
        fn_comments TBL_COMMENT.COMMENTS%type,
        fn_comment_date TBL_COMMENT.COMMENT_DATE%type
    ) is
    begin
        UPDATE TBL_COMMENT SET COMMENTS = fn_comments, COMMENT_DATE = fn_comment_date
        WHERE ID_COMMENT = fn_id_comment;
    end update_comment;

-- DELETE COMMENT
    PROCEDURE delete_comment(fn_id_comment TBL_COMMENT.ID_COMMENT%type) is
    begin
        Delete from TBL_COMMENT where ID_COMMENT = fn_id_comment;
    end delete_comment;*/

    END COMMENT_PCKG;
