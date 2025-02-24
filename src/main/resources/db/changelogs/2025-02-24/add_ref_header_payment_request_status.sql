insert into ref_headers (id, name, is_active)
values (100, 'Payment request status', true);

insert into refs (header_id, value, code, active, name)
values (100, 'payment.request.status.created', 1, true, 'CREATED'),
       (100, 'payment.request.status.in_progress', 2, true, 'IN PROGRESS'),
       (100, 'payment.request.status.canceled', 3, true, 'CANCELED'),
       (100, 'payment.request.status.expired', 4, true, 'EXPIRED'),
       (100, 'payment.request.status.declined', 5, true, 'DECLINED')