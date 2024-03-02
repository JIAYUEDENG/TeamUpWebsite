package org.alex.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.alex.website.entity.EnrollStatus;
import org.alex.website.mapper.EnrollMapper;
import org.alex.website.service.EnrollService;
import org.springframework.stereotype.Service;

@Service
public class EnrollServiceImpl extends ServiceImpl<EnrollMapper, EnrollStatus> implements EnrollService {
}
