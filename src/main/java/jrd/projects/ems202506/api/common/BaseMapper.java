package jrd.projects.ems202506.api.common;

import java.util.List;

public interface BaseMapper<Req, Res, Entity> {

	Res toDto(Entity entity);

	List<Res> toDtoList(List<Entity> entity);

	Entity toEntity(Req request);
}
