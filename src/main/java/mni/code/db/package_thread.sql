create or replace package package_thread is

    type thread_cursor is ref cursor;

    procedure get_all_thread(
        thread_list out thread_cursor);

    procedure get_single_thread(
        id_target in tbl_thread.id_thread%type,
        thread_list out thread_cursor);

    function insert_new_thread(thread_name varchar2, thread_date varchar2, thread_content varchar2)
        return number;   
        
    function update_current_thread(id_thread_target number, new_thread_name varchar2, new_thread_date varchar2, new_thread_content varchar2)
        return number;        

end package_thread;
/

create or replace package body package_thread is

    procedure get_all_thread(
        thread_list out thread_cursor) is
    begin
        open thread_list for
        select id_thread, thread_name, thread_date, thread_content
        from tbl_thread;
    end get_all_thread;

    procedure get_single_thread(
        id_target in tbl_thread.id_thread%type,
        thread_list out thread_cursor) is
    begin
        open thread_list for
            select id_thread, thread_name, thread_date, thread_content
            from tbl_thread
            where id_thread = id_target;
    end get_single_thread;

    function insert_new_thread(thread_name varchar2, thread_date varchar2, thread_content varchar2)
        return number 
        is
        id_thread_return tbl_thread.id_thread%type;
    begin
        insert into tbl_thread(thread_name, thread_date, thread_content)
        values (thread_name, thread_date, thread_content)
        returning id_thread into id_thread_return;
        return id_thread_return;
    end insert_new_thread;
    
    function update_current_thread(id_thread_target number, new_thread_name varchar2, new_thread_date varchar2, new_thread_content varchar2)
        return number
        is
        id_thread_return tbl_thread.id_thread%type;
    begin 
        update tbl_thread
        set thread_name = new_thread_name, thread_date = new_thread_date, thread_content = new_thread_content
        where id_thread = id_thread_target
        returning id_thread into id_thread_return;
        return id_thread_return;
    end update_current_thread;
    
end package_thread;
/