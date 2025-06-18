package jrd.projects.ems202506.api.common;

public interface BaseMapper<Req, Res, Entity> {

	Res toDto(Entity entity);

	Entity toEntity(Req request);
}
