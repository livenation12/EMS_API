package jrd.projects.ems202506.api.gallery_attachment;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.gallery_attachment.dto.GalleryAttachmentDto;
import jrd.projects.ems202506.api.gallery_attachment.dto.GalleryAttachmentRequest;

@Mapper
public interface GalleryAttachementMapper extends BaseMapper<GalleryAttachmentRequest, GalleryAttachmentDto, GalleryAttachment> {

	GalleryAttachementMapper INSTANCE = Mappers.getMapper(GalleryAttachementMapper.class);
}
