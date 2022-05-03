package course.java.dao.impl;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("DAO layer tests")
@SelectPackages("course.java.dao")
@IncludeClassNamePatterns(".*Test")
@IncludeTags("generic")
public class DaoTestsSuite {
}
