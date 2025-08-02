-- 初始化数据用户表
insert into gsc_sys.sys_user (id, username,auth_id,real_name, email, phone, is_enable,org_id, create_time, create_user_id, update_time, update_user_id)
VALUES (1, 'admin', 1, '管理员', 'admin@example.com', '13800000000', 'ENABLED', 1,DATE_TRUNC('second', NOW()), 1, DATE_TRUNC('second', NOW()), 1)
ON CONFLICT (id) DO NOTHING;

-- 组织表
INSERT INTO gsc_sys.sys_org (id, name, code, pid, org_level, order_num, is_enable, create_time, create_user_id, update_time, update_user_id)
VALUES (1, '集团总部', 'GROUP001', 0, 'GROUP', 1, 'ENABLED', DATE_TRUNC('second', NOW()), 1, DATE_TRUNC('second', NOW()), 1)
ON CONFLICT (id) DO NOTHING;
