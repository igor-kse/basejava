import edu.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static Resume getFilledResume( String uuid, String fullName ) {
        Resume resume = new Resume( uuid, fullName );

        // Contacts
        Map<ContactType, String> contacts = new HashMap<>();
        resume.setContacts( contacts );
        contacts.put( ContactType.MOBILE_PHONE, "+7(921) 855-0482" );
        contacts.put( ContactType.SKYPE, "grigory.kislin" );
        contacts.put( ContactType.EMAIL, "gkislin@yandex.ru" );
        contacts.put( ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin" );
        contacts.put( ContactType.GITHUB, "https://github.com/gkislin" );
        contacts.put( ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin" );
        contacts.put( ContactType.HOMEPAGE, "http://gkislin.ru/" );

        // Sections
        Map<SectionType, BaseSection> sections = new HashMap<>();
        resume.setSections( sections );

        // Objective
        TextSection objective = new TextSection();
        objective.setText( "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям" );
        sections.put( SectionType.OBJECTIVE, objective );

        // Personal
        TextSection personal = new TextSection();
        personal.setText( "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры." );
        sections.put( SectionType.PERSONAL, personal );

        // Achievement
        ListSection achievement = new ListSection();
        List<String> achievementList = new ArrayList<>();
        achievementList.add( "Организация команды и успешная реализация Java проектов для сторонних заказчиков..." );
        achievementList.add( "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"..." );
        achievementList.add( "Реализация двухфакторной аутентификации для онлайн платформы управления проектами..." );
        achievement.setList( achievementList );
        sections.put( SectionType.ACHIEVEMENT, achievement );

        // Qualification
        ListSection qualification = new ListSection();
        List<String> qualificationList = new ArrayList<>();
        qualificationList.add( "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2" );
        qualificationList.add( "Version control: Subversion, Git, Mercury, ClearCase, Perforce" );
        qualificationList.add( "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL" );
        qualificationList.add( "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy" );
        qualification.setList( qualificationList );
        sections.put( SectionType.QUALIFICATION, qualification );

        // Experience
        CompanySection experience = new CompanySection();
        List<Company> companies = new ArrayList<>();
        experience.setCompanies( companies );
        sections.put( SectionType.EXPERIENCE, experience );

        Company company = new Company( "Wrike", "https://www.wrike.com/", new ArrayList<>() );
        var periods = company.getPeriods();
        periods.add(
                new Period(
                        LocalDate.of( 2014, 10, 1 ),
                        LocalDate.of( 2016, 1, 1 ),
                        "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike, ..." )
        );
        companies.add( company );

        company = new Company( "RIT Center", "", new ArrayList<>() );
        periods = company.getPeriods();
        periods.add(
                new Period(
                        LocalDate.of( 2012, 4, 1 ),
                        LocalDate.of( 2012, 4, 1 ),
                        "Java архитектор",
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика,"
                )
        );
        companies.add( company );

        company = new Company( "Yota", "https://www.yota.ru/", new ArrayList<>() );
        periods = company.getPeriods();
        periods.add(
                new Period(
                        LocalDate.of( 2012, 4, 1 ),
                        LocalDate.of( 2012, 4, 1 ),
                        "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\"..."
                )
        );
        companies.add( company );


        // Education
        CompanySection education = new CompanySection();
        List<Company> educationCompanies = new ArrayList<>();
        education.setCompanies( educationCompanies );
        sections.put( SectionType.EDUCATION, education );

        Company educationCompany = new Company( "Siemens AG", "http://www.siemens.com/", new ArrayList<>() );
        educationCompanies.add( educationCompany );
        var educationPeriods = educationCompany.getPeriods();
        educationPeriods.add(
                new Period(
                        LocalDate.of( 2005, 1, 1 ),
                        LocalDate.of( 2005, 4, 1 ),
                        "3 месяца обучения мобильным IN сетям (Берлин)",
                        "")
        );
        educationCompanies.add( educationCompany );

        educationCompany = new Company( "Alcatel", "http://www.alcatel.ru/", new ArrayList<>() );
        educationCompanies.add( educationCompany );
        educationPeriods = educationCompany.getPeriods();
        educationPeriods.add(
                new Period(
                        LocalDate.of( 1997, 9, 1 ),
                        LocalDate.of( 1998, 3, 1 ),
                        "6 месяцев обучения цифровым телефонным сетям (Москва)",
                        "")
        );

        return resume;
    }
}
