package com.lwd.gsc.module.sys.service.impl;

import com.lwd.gsc.common.base.BaseRepository;
import com.lwd.gsc.common.base.BaseServiceImpl;
import com.lwd.gsc.module.sys.model.entity.SysUser;
import com.lwd.gsc.module.sys.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author lwd
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,Long>  implements SysUserService {

    public SysUserServiceImpl(BaseRepository<SysUser, Long> repository) {
        super(repository);
    }
}
