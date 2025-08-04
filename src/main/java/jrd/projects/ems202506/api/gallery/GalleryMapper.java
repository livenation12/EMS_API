package jrd.projects.ems202506.api.gallery;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import jrd.projects.ems202506.api.common.BaseMapper;
import jrd.projects.ems202506.api.common.FileMetadata;
import jrd.projects.ems202506.api.gallery.dto.GalleryDto;
import jrd.projects.ems202506.api.gallery.dto.GalleryRequest;
import jrd.projects.ems202506.api.gallery_attachment.GalleryAttachment;
import jrd.projects.ems202506.api.user.UserMapper;

@Mapper(uses = {UserMapper.class})
public interface GalleryMapper extends BaseMapper<GalleryRequest, GalleryDto, Gallery> {

	GalleryMapper INSTANCE = Mappers.getMapper(GalleryMapper.class);

	GalleryAttachment toAttachment(FileMetadata file);

	List<GalleryAttachment> toAttachmentList(List<FileMetadata> file);

}
