package org.alex.website.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.alex.website.entity.Project;
import org.alex.website.mapper.ProjectMapper;
import org.alex.website.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
}
