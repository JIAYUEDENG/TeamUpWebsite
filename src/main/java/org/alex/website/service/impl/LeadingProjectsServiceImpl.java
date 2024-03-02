package org.alex.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.alex.website.entity.LeadingProjects;
import org.alex.website.mapper.LeadingProjectsMapper;
import org.alex.website.service.LeadingProjectsService;
import org.springframework.stereotype.Service;

@Service
public class LeadingProjectsServiceImpl extends ServiceImpl<LeadingProjectsMapper, LeadingProjects> implements LeadingProjectsService {
}
