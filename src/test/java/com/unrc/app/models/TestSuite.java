package com.unrc.app.models;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Suite.class)
@Suite.SuiteClasses( {  OwnerSpec.class ,CitySpec.class, RealEstateSpec.class, AddressSpec.class} )



public class TestSuite {
}