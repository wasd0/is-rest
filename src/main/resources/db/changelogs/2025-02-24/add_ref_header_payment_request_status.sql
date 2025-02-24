insert into ref_headers (id, name, is_active)
values (100, 'Payment request status', true);

insert into refs (header_id, value, code, active)
values (100, 'payment.request.status.created', 1, true),
       (100, 'payment.request.status.in_progress', 2, true),
       (100, 'payment.request.status.canceled', 3, true),
       (100, 'payment.request.status.expired', 4, true),
       (100, 'payment.request.status.declined', 5, true)