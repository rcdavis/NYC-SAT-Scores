package com.rendavis.nycsatscores;

import com.rendavis.nycsatscores.school.Address;
import com.rendavis.nycsatscores.school.SATScores;
import com.rendavis.nycsatscores.school.School;
import com.rendavis.nycsatscores.school.SchoolDTO;
import com.rendavis.nycsatscores.school.SchoolSATDTO;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static List<School> createMockSchoolsList() {
        final List<School> schools = new ArrayList<>();
        schools.add(new School(
            "02M260",
            "Clinton School Writers & Artists, M.S. 260",
            "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.",
            "212-524-4360",
            "www.theclintonschool.net",
            new Address("10 East 15th Street", "Manhattan", 10003, "NY"),
            new SATScores(
                "02M260",
                "Clinton School Writers & Artists, M.S. 260",
                443,
                419,
                426,
                410
            )
        ));
        schools.add(new School(
            "21K728",
            "Liberation Diploma Plus High School",
            "The mission of Liberation Diploma Plus High School, in partnership with CAMBA, is to develop the student academically, socially, and emotionally. We will equip students with the skills needed to evaluate their options so that they can make informed and appropriate choices and create personal goals for success. Our year-round model (trimesters plus summer school) provides students the opportunity to gain credits and attain required graduation competencies at an accelerated rate. Our partners offer all students career preparation and college exposure. Students have the opportunity to earn college credit(s). In addition to fulfilling New York City graduation requirements, students are required to complete a portfolio to receive a high school diploma.",
            "718-946-6812",
            "schools.nyc.gov/schoolportals/21/K728",
            new Address("2865 West 19th Street", "Brooklyn", 11224, "NY"),
            new SATScores(
                "21K728",
                "Liberation Diploma Plus",
                10,
                411,
                369,
                373
            )
        ));
        schools.add(new School(
            "08X282",
            "Women's Academy of Excellence",
            "The Women’s Academy of Excellence is an all-girls public high school, serving grades 9-12. Our mission is to create a community of lifelong learners, to nurture the intellectual curiosity and creativity of young women and to address their developmental needs. The school community cultivates dynamic, participatory learning, enabling students to achieve academic success at many levels, especially in the fields of math, science, and civic responsibility. Our scholars are exposed to a challenging curriculum that encourages them to achieve their goals while being empowered to become young women and leaders. Our Philosophy is GIRLS MATTER!",
            "718-542-0740",
            "schools.nyc.gov/SchoolPortals/08/X282",
            new Address("456 White Plains Road", "Bronx", 10473, "NY"),
            new SATScores(
                "08X282",
                "Women's Academy of Excellence",
                44,
                407,
                386,
                378
            )
        ));
        return schools;
    }

    public static School createMockSchool() {
        return new School(
            "02M260",
            "Clinton School Writers & Artists, M.S. 260",
            "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.",
            "212-524-4360",
            "www.theclintonschool.net",
            new Address("10 East 15th Street", "Manhattan", 10003, "NY"),
            new SATScores(
                "02M260",
                "Clinton School Writers & Artists, M.S. 260",
                443,
                419,
                426,
                410
            )
        );
    }

    public static List<SchoolDTO> createMockSchoolDtos() {
        final List<SchoolDTO> dtos = new ArrayList<>();

        final SchoolDTO dto1 = new SchoolDTO();
        dto1.id = "02M260";
        dto1.name = "Clinton School Writers & Artists, M.S. 260";
        dto1.overview = "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.";
        dto1.phoneNumber = "212-524-4360";
        dto1.websiteUrl = "www.theclintonschool.net";
        dto1.primaryAddressLine1 = "10 East 15th Street";
        dto1.city = "Manhattan";
        dto1.zip = 10003;
        dto1.stateCode = "NY";
        dtos.add(dto1);

        final SchoolDTO dto2 = new SchoolDTO();
        dto2.id = "21K728";
        dto2.name = "Liberation Diploma Plus High School";
        dto2.overview = "The mission of Liberation Diploma Plus High School, in partnership with CAMBA, is to develop the student academically, socially, and emotionally. We will equip students with the skills needed to evaluate their options so that they can make informed and appropriate choices and create personal goals for success. Our year-round model (trimesters plus summer school) provides students the opportunity to gain credits and attain required graduation competencies at an accelerated rate. Our partners offer all students career preparation and college exposure. Students have the opportunity to earn college credit(s). In addition to fulfilling New York City graduation requirements, students are required to complete a portfolio to receive a high school diploma.";
        dto2.phoneNumber = "718-946-6812";
        dto2.websiteUrl = "schools.nyc.gov/schoolportals/21/K728";
        dto2.primaryAddressLine1 = "2865 West 19th Street";
        dto2.city = "Brooklyn";
        dto2.zip = 11224;
        dto2.stateCode = "NY";
        dtos.add(dto2);

        final SchoolDTO dto3 = new SchoolDTO();
        dto3.id = "08X282";
        dto3.name = "Women's Academy of Excellence";
        dto3.overview = "The Women’s Academy of Excellence is an all-girls public high school, serving grades 9-12. Our mission is to create a community of lifelong learners, to nurture the intellectual curiosity and creativity of young women and to address their developmental needs. The school community cultivates dynamic, participatory learning, enabling students to achieve academic success at many levels, especially in the fields of math, science, and civic responsibility. Our scholars are exposed to a challenging curriculum that encourages them to achieve their goals while being empowered to become young women and leaders. Our Philosophy is GIRLS MATTER!";
        dto3.phoneNumber = "718-542-0740";
        dto3.websiteUrl = "schools.nyc.gov/SchoolPortals/08/X282";
        dto3.primaryAddressLine1 = "456 White Plains Road";
        dto3.city = "Bronx";
        dto3.zip = 10473;
        dto3.stateCode = "NY";
        dtos.add(dto3);

        return dtos;
    }

    public static List<SchoolSATDTO> createMockSatDtos() {
        final List<SchoolSATDTO> dtos = new ArrayList<>();

        final SchoolSATDTO dto1 = new SchoolSATDTO();
        dto1.id = "02M260";
        dto1.name = "Clinton School Writers & Artists, M.S. 260";
        dto1.numSATTestTakers = "443";
        dto1.averageSATCriticalReadingScore = "419";
        dto1.averageSATMathScore = "426";
        dto1.averageSATWritingScore = "410";
        dtos.add(dto1);

        final SchoolSATDTO dto2 = new SchoolSATDTO();
        dto2.id = "21K728";
        dto2.name = "Liberation Diploma Plus";
        dto2.numSATTestTakers = "10";
        dto2.averageSATCriticalReadingScore = "411";
        dto2.averageSATMathScore = "369";
        dto2.averageSATWritingScore = "373";
        dtos.add(dto2);

        final SchoolSATDTO dto3 = new SchoolSATDTO();
        dto3.id = "08X282";
        dto3.name = "Women's Academy of Excellence";
        dto3.numSATTestTakers = "44";
        dto3.averageSATCriticalReadingScore = "407";
        dto3.averageSATMathScore = "386";
        dto3.averageSATWritingScore = "378";
        dtos.add(dto3);

        return dtos;
    }
}
