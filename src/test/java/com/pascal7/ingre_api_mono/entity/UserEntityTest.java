package com.pascal7.ingre_api_mono.entity;

import com.pascal7.ingre_api_mono.custom.VerificationStat;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserEntityTest {

    @Test
    void getUserObject_shouldReturnNotNull_when_userObjectInitiated(){
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void getPhoneNumber_shouldReturnNotNull_when_userCreatedAndPhoneNumberAssigned() {
        User user = new User();
        user.setPhoneNumber("0812345678");
        assertNotNull(user.getPhoneNumber());
    }

    @Test
    void getPhoneNumber_shouldReturnNotSamePhoneNumber_when_userCreatedAndPhoneNumberAssigned() {
        User user = new User();
        String number = "0812345678";
        user.setPhoneNumber(number);
        assertEquals(number, user.getPhoneNumber());
    }

    @Test
    void getBirthDate_shouldReturnNotNull_when_userCreatedAndBirthDateAssigned(){
        User user = new User();
        user.setBirthDate("1997-28-04");
        assertNotNull(user.getBirthDate());
    }

    @Test
    void getBirthDate_shouldReturnSameString_when_userCreatedAndBirthDateAssigned() {
        User user = new User();
        String date = "1997-04-28";
        user.setBirthDate(date);
        assertEquals(date, user.getBirthDate());
    }

    @Test
    void getId_shouldReturnNotNull_when_userCreatedAndIdAssigned() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        assertNotNull(user.getId());
    }

    @Test
    void getId_shouldReturnSameUUID_when_userCreatedAndIdAssigned() {
        User user = new User();
        String id = UUID.randomUUID().toString();
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    void getFullName_shouldReturnNotNull_when_userCreatedAndFullNameAssigned() {
        User user = new User();
        user.setFullName("Thomas Alfa Edison");
        assertNotNull(user.getFullName());
    }

    @Test
    void getFullName_shouldReturnStringFullName_when_userCreatedAndFullNameAssigned() {
        User user = new User();
        String fullName = "Edward GrahamBell";
        user.setFullName(fullName);
        assertEquals(fullName, user.getFullName());
    }

    @Test
    void getEmail_shouldReturnNotNull_when_userCreatedAndEmailAssigned() {
        User user = new User();
        ReflectionTestUtils.setField(user, "email", "jerukkeras@gmail.com");
        assertNotNull(user.getEmail());
    }

    @Test
    void getEmail_shouldReturnStringEmail_when_userCreatedAndEmailAssigned() {
        User user = new User();
        String email = "adminingre@gmail.com";
        ReflectionTestUtils.setField(user, "email", email);
        assertEquals(email, user.getEmail());
    }

    @Test
    void getPassword_shouldReturnNotNull_when_userCreatedAndPasswordAssigned() {
        User user = new User();
        user.setPassword("123");
        assertNotNull(user.getPassword());
    }

    @Test
    void getPassword_shouldReturnStringPassword_when_userCreatedAndPasswordAssigned() {
        User user = new User();
        String password = "2352";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    void getGender_shouldReturnNotNull_when_userCreatedAndGenderAssigned() {
        User user = new User();
        user.setGender("Male");
        assertNotNull(user.getGender());
    }

    @Test
    void getGender_shouldReturnStringGender_when_userCreatedAndGenderAssigned() {
        User user = new User();
        String gender = "Female";
        user.setGender(gender);
        assertEquals(gender, user.getGender());
    }

    @Test
    void getDateCreated_shouldReturnNotNull_when_userCreatedAndDateCreatedAssigned() throws ParseException {
        User user = new User();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        ReflectionTestUtils.setField(user, "dateCreated", new Timestamp(sdf.parse("2021-12-12").getTime()));
        assertNotNull(user.getDateCreated());
    }

    @Test
    void getDateCreated_shouldReturnStringDateCreated_when_userCreatedAndDateCreatedAssigned() throws ParseException {
        User user = new User();
        String date = "2021-12-12";
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        ReflectionTestUtils.setField(user, "dateCreated", new Timestamp(sdf.parse(date).getTime()));
        assertEquals(date, sdf.format(user.getDateCreated()));
    }

    @Test
    void getPhoto_shouldReturnNotNull_when_userCreatedAndPhotoAssigned() {
        User user = new User();
        user.setPhoto("photo.jpg");
        assertNotNull(user.getPhoto());
    }

    @Test
    void getPhoto_shouldReturnStringPhoto_when_userCreatedAndPhotoAssigned() {
        User user = new User();
        String photo = "image.png";
        user.setPhoto(photo);
        assertEquals(photo, user.getPhoto());
    }

    @Test
    void getRole_shouldReturnNotNull_when_userCreatedAndRoleAssigned(){
        User user = new User();
        user.setRole("user");
        assertNotNull(user.getRole());
    }

    @Test
    void getRole_shouldReturnStringRole_when_userCreatedAndRoleAssigned(){
        User user = new User();
        String role = "user";
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @Test
    void getVerificationStat_shouldReturnNotNull_when_userCreatedAndVerificationStatAssigned(){
        User user = new User();
        user.setVerificationStat(VerificationStat.VERIFIED.getValue());
        assertNotNull(user.getVerificationStat());
    }

    @Test
    void getVerificationStat_shouldReturnStringVerificationStat_when_userCreatedAndVerificationStatAssigned(){
        User user = new User();
        user.setVerificationStat(VerificationStat.UNVERIFIED.getValue());
        assertEquals(VerificationStat.UNVERIFIED.getValue() ,user.getVerificationStat());
    }
}