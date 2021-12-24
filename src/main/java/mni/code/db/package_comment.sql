create or replace procedure get_comment_by_id(
    c_id in tbl_comment.id_comment%type,
    c_content out tbl_comment.content_comment%type,
    c_date out tbl_comment.date_comment%type)
as
begin
    select content_comment, date_comment into c_content, c_date from tbl_comment where id_comment = c_id;
end;

create or replace function insertNewComment(content_comment in varchar2, date_comment in varchar2)
    return number
    is
    l_id tbl_comment.id_comment%type;
begin
    insert into tbl_comment(content_comment, date_comment) values(content_comment, to_date(date_comment, 'yyyy/mm/dd'))
    returning id_comment into l_id;
    return l_id;
end;

create or replace function update_current_comment(id_c number,content_c varchar2, date_c varchar2)
    return number
    is
    l_id tbl_comment.id_comment%type;
begin
    update tbl_comment
    set content_comment = content_c, date_comment = to_date(date_c, 'yyyy/mm/dd')
    where id_comment = id_c
    returning id_comment into l_id;
    return l_id;
end;
/

create or replace package comment_cursor is

    type c_cursor is ref cursor;

    procedure get_all_comments(
        comment_list_cursor out c_cursor);

    procedure get_comment_by_id(
        c_id in tbl_comment.id_comment%type,
        comment_list_cursor out c_cursor);

    function insert_New_Comment(content_comment in varchar2, date_comment in varchar2)
        return number;

    function update_current_comment(id_c number,content_c varchar2, date_c varchar2)
        return number;

end comment_cursor;
/

create or replace package body comment_cursor is

    procedure get_all_comments(
        comment_list_cursor out c_cursor) is
    begin
        open comment_list_cursor for
            select id_comment, content_comment, date_comment
            from tbl_comment;
    end get_all_comments;

    procedure get_comment_by_id(
        c_id in tbl_comment.id_comment%type,
        comment_list_cursor out c_cursor) is
    begin
        open comment_list_cursor for
            select id_comment, content_comment, date_comment
            from tbl_comment
            where id_comment = c_id;
    end get_comment_by_id;

    function insert_New_Comment(content_comment in varchar2, date_comment in varchar2)
        return number
        is
        l_id tbl_comment.id_comment%type;
    begin
        insert into tbl_comment(content_comment, date_comment) values(content_comment, to_date(date_comment, 'yyyy/mm/dd'))
        returning id_comment into l_id;
        return l_id;
    end insert_New_Comment;

    function update_current_comment(id_c number,content_c varchar2, date_c varchar2)
        return number
        is
        l_id tbl_comment.id_comment%type;
    begin
        update tbl_comment
        set content_comment = content_c, date_comment = to_date(date_c, 'yyyy/mm/dd')
        where id_comment = id_c
        returning id_comment into l_id;
        return l_id;
    end update_current_comment;

end comment_cursor;
/