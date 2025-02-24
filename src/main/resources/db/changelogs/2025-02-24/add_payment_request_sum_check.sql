alter table payment_requests
    add constraint sum_check check ( sum > 0 );