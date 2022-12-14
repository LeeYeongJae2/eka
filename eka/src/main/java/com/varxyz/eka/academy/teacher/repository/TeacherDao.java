package com.varxyz.eka.academy.teacher.repository;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.eka.academy.academy.domain.Academy;
import com.varxyz.eka.academy.teacher.command.TeacherListCommand;
import com.varxyz.eka.academy.teacher.domain.SubjectCategory;
import com.varxyz.eka.academy.teacher.domain.Teacher;

@Repository
public class TeacherDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TeacherDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 강사 등록
	public void addTeacher(Teacher teacher) {
		String sql = "INSERT INTO Teacher(academyId, name, gender, ssn, phone, subject, "
				+ " education, career, image, foreigner)" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, teacher.getAcademy().getAid(), teacher.getName(), teacher.getGender(),
				teacher.getSsn(), teacher.getPhone(), teacher.getSubject(), teacher.getEducation(), teacher.getCareer(),
				teacher.getImage(), teacher.getForeigner());
		System.out.println("add 완료");
	}

	// 강사 정보 수정
	public void updateTeacher(TeacherListCommand teacher) {
		String sql = "UPDATE Teacher SET name=?, gender=?, ssn=?, phone=?, subject=?,"
				+ " education=?, career=?, image=?, foreigner=? WHERE tid=?";

		jdbcTemplate.update(sql, teacher.getName(), teacher.getGender(), teacher.getSsn(),
				teacher.getPhone(), teacher.getSubject(), teacher.getEducation(), 
				teacher.getCareer(), teacher.getImage(), teacher.getForeigner(), teacher.getTid());
	}

	// 강사 전체조회
	public List<Teacher> findAllAcademyTeacher(Academy academy) {
		String sql = "SELECT * FROM Teacher WHERE academyId=?";
		// teacherfindList로 변경하기
		return jdbcTemplate.query(sql, new TeacherRowMapper(), academy.getAid());
	}
	
	// 강사 삭제하기
	public void deleteTeacher(long tid) {
		String sql="DELETE FROM Teacher WHERE tid = ?";
		jdbcTemplate.update(sql, tid);
	}

	// 과목으로 강사 조회
	public List<Teacher> findTeacherBySubject(Academy academy, String subject) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND subject=?";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),subject);
	}

	// 성별로 강사 조회
	public List<Teacher> findTeacherByGender(Academy academy, String gender) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND gender=?";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),gender);
	}
	
	// 외국인여부로 강사 조회
	public List<Teacher> findTeacherByForeigner(Academy academy, String foreigner) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND foreigner=?";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),foreigner);
	}
	
	// 강사명으로 강사 조회
	public List<Teacher> findTeacherByName(Academy academy, String name) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND name LIKE ?";
		String likeName = "%"+name+"%";
		return jdbcTemplate.query(sql, new TeacherRowMapper(), academy.getAid(), likeName);
	}
	
	// 강사id로 강사 상세정보 조회
	public List<Teacher> findTeacherByTid(Academy academy, long teacherId) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND teacherId=?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Teacher>(Teacher.class), academy.getAid(),teacherId);
	}
	
	
	// 과목 조회
	public List<SubjectCategory> findSubjectCategory() {
		String sql = "SELECT * FROM SubjectCategory";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<SubjectCategory>(SubjectCategory.class));
	}

	public List<Teacher> findTeacherByAll(Academy academy, String subject, String gender, String foreigner,
			String name) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND name LIKE ? AND subject =? AND foreigner=?";
		String likeName = "%"+name+"%";
		return jdbcTemplate.query(sql, new TeacherRowMapper(), academy.getAid(), likeName, subject, foreigner);
	}

	public List<Teacher> findTeacherBySubjectGender(Academy academy, String subject, String gender) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND subject =? AND gender=?";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),subject, gender);
	}

	public List<Teacher> findTeacherBySubjectForeign(Academy academy, String subject, String foreigner) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND subject =? AND foreigner=?";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),subject, foreigner);
	}

	public List<Teacher> findTeacherBySubjectName(Academy academy, String subject, String name) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND subject =? AND name LIKE ?";
		String likeName = "%"+name+"%";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),subject, likeName);
	}

	public List<Teacher> findTeacherByGenderForeign(Academy academy, String gender, String foreigner) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND gender =? AND foreigner=?";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),gender, foreigner);
	}

	public List<Teacher> findTeacherByGenderName(Academy academy, String gender, String name) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND gender =? AND name LIKE ?";
		String likeName = "%"+name+"%";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),gender, likeName);
	}

	public List<Teacher> findTeacherByForeignName(Academy academy, String foreigner, String name) {
		String sql = "SELECT * FROM Teacher WHERE academyId=? AND foreigner =? AND name LIKE ?";
		String likeName = "%"+name+"%";
		return jdbcTemplate.query(sql, new TeacherRowMapper(),academy.getAid(),foreigner, likeName);
	}

}
