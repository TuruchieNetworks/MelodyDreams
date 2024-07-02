package com.turuchie.melodydreams.utils.CountryCodeUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.turuchie.melodydreams.models.CountryCodes;
@Component
public class PhoneNumberParser {
    public void parsePhoneNumbers(Model model) {
        final String phoneNumberList = "\r\n"
        		+ "Afghanistan	93	+04:30	\r\n"
        		+ "Åland	358 (18)	+02:00	+03:00\r\n"
        		+ "Albania	355	+01:00	+02:00\r\n"
        		+ "Algeria	213	+01:00	\r\n"
        		+ "American Samoa	1 (684)	−11:00	\r\n"
        		+ "Andorra	376	+01:00	+02:00\r\n"
        		+ "Angola	244	+01:00	\r\n"
        		+ "Anguilla	1 (264)	−04:00	\r\n"
        		+ "Antigua and Barbuda	1 (268)	−04:00	\r\n"
        		+ "Argentina	54	−03:00	\r\n"
        		+ "Armenia	374	+04:00	\r\n"
        		+ "Aruba	297	−04:00	\r\n"
        		+ "Ascension	247	+00:00	\r\n"
        		+ "Australia	61	+08:00\r\n"
//        		+ "to\r\n"
//        		+ "10:30	+08:00\r\n"
//        		+ "to\r\n"
//        		+ "11:00\r\n"
        		+ "Austria	43	+01:00	+02:00\r\n"
        		+ "Azerbaijan	994	+04:00	\r\n"
        		+ "Bahamas	1 (242)	−05:00	−04:00\r\n"
        		+ "Bahrain	973	+03:00	\r\n"
        		+ "Bangladesh	880	+06:00	\r\n"
        		+ "Barbados	1 (246)	−04:00	\r\n"
        		+ "Belarus	375	+03:00	\r\n"
        		+ "Belgium	32	+01:00	+02:00\r\n"
        		+ "Belize	501	−06:00	\r\n"
        		+ "Benin	229	+01:00	\r\n"
        		+ "Bermuda	1 (441)	−04:00	−03:00\r\n"
        		+ "Bhutan	975	+06:00	\r\n"
        		+ "Bolivia	591	−04:00	\r\n"
        		+ "Bonaire	599 (7)	−04:00	\r\n"
        		+ "Bosnia and Herzegovina	387	+01:00	+02:00\r\n"
        		+ "Botswana	267	+02:00	\r\n"
        		+ "Brazil	55	−05:00\r\n"
//        		+ "to\r\n"
//        		+ "–02:00	\r\n"
        		+ "British Indian Ocean Territory (Diego Garcia)	246	+06:00	\r\n"
        		+ "British Virgin Islands	1 (284)	−04:00	\r\n"
        		+ "Brunei Darussalam	673	+08:00	\r\n"
        		+ "Bulgaria	359	+02:00	+03:00\r\n"
        		+ "Burkina Faso	226	+00:00	\r\n"
        		+ "Burundi	257	+02:00	\r\n"
        		+ "Cape Verde	238	−01:00	\r\n"
        		+ "Cambodia	855	+07:00	\r\n"
        		+ "Cameroon	237	+01:00	\r\n"
        		+ "Canada	1	−08:00\r\n"
//        		+ "to\r\n"
//        		+ "–03:30	−07:00\r\n"
//        		+ "to\r\n"
//        		+ "–02:30\r\n"
        		+ "Caribbean Netherlands	599 (3,4,7)	−04:00	\r\n"
        		+ "Cayman Islands	1 (345)	−05:00	\r\n"
        		+ "Central African Republic	236	+01:00	\r\n"
        		+ "Chad	235	+01:00	\r\n"
        		+ "Chile	56	−06:00\r\n"
//        		+ "to\r\n"
//        		+ "–04:00	−05:00\r\n"
//        		+ "to\r\n"
//        		+ "–03:00\r\n"
        		+ "China	86	+08:00	\r\n"
        		+ "Christmas Island	61 (89164)	+07:00	\r\n"
        		+ "Cocos (Keeling) Islands	61 (89162)	+06:30	\r\n"
        		+ "Colombia	57	−05:00	\r\n"
        		+ "Comoros	269	+03:00	\r\n"
        		+ "Congo	242	+01:00	\r\n"
        		+ "Congo, Democratic Republic of the	243	+01:00\r\n"
//        		+ "to\r\n"
//        		+ "02:00	\r\n"
        		+ "Cook Islands	682	−10:00	\r\n"
        		+ "Costa Rica	506	−06:00	\r\n"
        		+ "Ivory Coast (Côte d'Ivoire)	225	+00:00	\r\n"
        		+ "Croatia	385	+01:00	+02:00\r\n"
        		+ "Cuba	53	−05:00	−04:00\r\n"
        		+ "Curaçao	599 (9)	−04:00	\r\n"
        		+ "Cyprus	357	+02:00	+03:00\r\n"
        		+ "Czech Republic	420	+01:00	+02:00\r\n"
        		+ "Denmark	45	+01:00	+02:00\r\n"
        		+ "Djibouti	253	+03:00	\r\n"
        		+ "Dominica	1 (767)	−04:00	\r\n"
        		+ "Dominican Republic	1 (809, 829, 849)	−04:00	\r\n"
        		+ "Easter Island	56	−06:00	−05:00\r\n"
        		+ "Ecuador	593	−06:00\r\n"
//        		+ "to\r\n"
//        		+ "–05:00	\r\n"
        		+ "Egypt	20	+02:00	+03:00\r\n"
        		+ "El Salvador	503	−06:00	\r\n"
        		+ "Ellipso (Mobile Satellite service)	881 (2, 3)		\r\n"
        		+ "EMSAT (Mobile Satellite service)	882 (13)		\r\n"
        		+ "Equatorial Guinea	240	+01:00	\r\n"
        		+ "Eritrea	291	+03:00	\r\n"
        		+ "Estonia	372	+02:00	+03:00\r\n"
        		+ "Eswatini	268	+02:00	\r\n"
        		+ "Ethiopia	251	+03:00	\r\n"
        		+ "Falkland Islands	500	−03:00	\r\n"
        		+ "Faroe Islands	298	+00:00	+01:00\r\n"
        		+ "Fiji	679	+12:00	+13:00\r\n"
        		+ "Finland	358	+02:00	+03:00\r\n"
        		+ "France	33	+01:00	+02:00\r\n"
        		+ "French Antilles	596		\r\n"
        		+ "French Guiana	594	−03:00	\r\n"
        		+ "French Polynesia	689	−10:00\r\n"
//        		+ "to\r\n"
//        		+ "–09:00	\r\n"
        		+ "Gabon	241	+01:00	\r\n"
        		+ "Gambia	220	+00:00	\r\n"
        		+ "Georgia	995	+04:00	\r\n"
        		+ "Germany	49	+01:00	+02:00\r\n"
        		+ "Ghana	233	+00:00	\r\n"
        		+ "Gibraltar	350	+01:00	+02:00\r\n"
        		+ "Global Mobile Satellite System (GMSS)	881		\r\n"
        		+ "Globalstar (Mobile Satellite Service)	881 (8, 9)		\r\n"
        		+ "Greece	30	+02:00	+03:00\r\n"
        		+ "Greenland	299	−04:00\r\n"
//        		+ "to\r\n"
//        		+ "01:00	−03:00\r\n"
//        		+ "to\r\n"
//        		+ "00:00\r\n"
        		+ "Grenada	1 (473)	−04:00	\r\n"
        		+ "Guadeloupe	590	−04:00	\r\n"
        		+ "Guam	1 (671)	+10:00	\r\n"
        		+ "Guatemala	502	−06:00	\r\n"
        		+ "Guernsey	44 (1481, 7781, 7839, 7911)	+00:00	+01:00\r\n"
        		+ "Guinea	224	+00:00	\r\n"
        		+ "Guinea-Bissau	245	+00:00	\r\n"
        		+ "Guyana	592	−04:00	\r\n"
        		+ "Haiti	509	−05:00	−04:00\r\n"
        		+ "Honduras	504	−06:00	\r\n"
        		+ "Hong Kong	852	+08:00	\r\n"
        		+ "Hungary	36	+01:00	+02:00\r\n"
        		+ "Iceland	354	+00:00	\r\n"
        		+ "ICO Global (Mobile Satellite Service)	881 (0, 1)		\r\n"
        		+ "India	91	+05:30	\r\n"
        		+ "Indonesia	62	+07:00\r\n"
//        		+ "to\r\n"
//        		+ "09:00	\r\n"
        		+ "Inmarsat SNAC	870		\r\n"
        		+ "International Freephone Service (UIFN)	800		\r\n"
        		+ "International Networks	882, 883		\r\n"
        		+ "International Premium Rate Service	979		\r\n"
        		+ "International Shared Cost Service (ISCS)	808		\r\n"
        		+ "Iran	98	+03:30	+04:30\r\n"
        		+ "Iraq	964	+03:00	\r\n"
        		+ "Ireland	353	+00:00	+01:00\r\n"
        		+ "Iridium (Mobile Satellite service)	881 (6, 7)		\r\n"
        		+ "Isle of Man	44 (1624, 7524, 7624, 7924)	+00:00	+01:00\r\n"
        		+ "Israel	972	+02:00	+03:00\r\n"
        		+ "Italy	39	+01:00	+02:00\r\n"
        		+ "Jamaica	1 (658, 876)	−05:00	\r\n"
        		+ "Jan Mayen	47 (79)	+01:00	+02:00\r\n"
        		+ "Japan	81	+09:00	\r\n"
        		+ "Jersey	44 (1534)	+00:00	+01:00\r\n"
        		+ "Jordan	962	+02:00	+03:00\r\n"
        		+ "Kazakhstan	7 (6, 7) (997 assigned but now abandoned)	+05:00\r\n"
//        		+ "to\r\n"
//        		+ "06:00	\r\n"
        		+ "Kenya	254	+03:00	\r\n"
        		+ "Kiribati	686	+12:00\r\n"
//        		+ "to\r\n"
//        		+ "14:00	\r\n"
        		+ "Korea, North	850	+09:00	\r\n"
        		+ "Korea, South	82	+09:00	\r\n"
        		+ "Kosovo	383	+01:00	+02:00\r\n"
        		+ "Kuwait	965	+03:00	\r\n"
        		+ "Kyrgyzstan	996	+06:00	\r\n"
        		+ "Laos	856	+07:00	\r\n"
        		+ "Latvia	371	+02:00	+03:00\r\n"
        		+ "Lebanon	961	+02:00	+03:00\r\n"
        		+ "Lesotho	266	+02:00	\r\n"
        		+ "Liberia	231	+00:00	\r\n"
        		+ "Libya	218	+02:00	\r\n"
        		+ "Liechtenstein	423	+01:00	+02:00\r\n"
        		+ "Lithuania	370	+02:00	+03:00\r\n"
        		+ "Luxembourg	352	+01:00	+02:00\r\n"
        		+ "Macau	853	+08:00	\r\n"
        		+ "Madagascar	261	+03:00	\r\n"
        		+ "Malawi	265	+02:00	\r\n"
        		+ "Malaysia	60	+08:00	\r\n"
        		+ "Maldives	960	+05:00	\r\n"
        		+ "Mali	223	+00:00	\r\n"
        		+ "Malta	356	+01:00	+02:00\r\n"
        		+ "Marshall Islands	692	+12:00	\r\n"
        		+ "Martinique	596	−04:00	\r\n"
        		+ "Mauritania	222	+00:00	\r\n"
        		+ "Mauritius	230	+04:00	\r\n"
        		+ "Mayotte	262 (269, 639)	+03:00	\r\n"
        		+ "Mexico	52	−08:00\r\n"
//        		+ "to\r\n"
//        		+ "–05:00	−07:00\r\n"
//        		+ "to\r\n"
//        		+ "–05:00\r\n"
        		+ "Micronesia, Federated States of	691	+10:00\r\n"
//        		+ "to\r\n"
//        		+ "11:00	\r\n"
        		+ "Moldova	373	+02:00	+03:00\r\n"
        		+ "Monaco	377	+01:00	+02:00\r\n"
        		+ "Mongolia	976	+07:00\r\n"
//        		+ "to\r\n"
//        		+ "08:00	\r\n"
        		+ "Montenegro	382	+01:00	+02:00\r\n"
        		+ "Montserrat	1 (664)	−04:00	\r\n"
        		+ "Morocco	212	+01:00	\r\n"
        		+ "Mozambique	258	+02:00	\r\n"
        		+ "Myanmar	95	+06:30	\r\n"
        		+ "Namibia	264	+02:00	\r\n"
        		+ "Nauru	674	+12:00	\r\n"
        		+ "  Nepal	977	+05:45	\r\n"
        		+ "Netherlands	31	+01:00	+02:00\r\n"
        		+ "Nevis	1 (869)	−04:00	\r\n"
        		+ "New Caledonia	687	+11:00	\r\n"
        		+ "New Zealand	64	+12:00	+13:00\r\n"
        		+ "Nicaragua	505	−06:00	\r\n"
        		+ "Niger	227	+01:00	\r\n"
        		+ "Nigeria	234	+01:00	\r\n"
        		+ "Niue	683	−11:00	\r\n"
        		+ "Norfolk Island	672 (3)	+11:00	\r\n"
        		+ "North Macedonia	389	+01:00	+02:00\r\n"
        		+ "Northern Cyprus	90 (392)	+02:00	+03:00\r\n"
        		+ "Northern Ireland	44 (28)	+00:00	+01:00\r\n"
        		+ "Northern Mariana Islands	1 (670)	+10:00	\r\n"
        		+ "Norway	47	+01:00	+02:00\r\n"
        		+ "Oman	968	+04:00	\r\n"
        		+ "Pakistan	92	+05:00	\r\n"
        		+ "Palau	680	+09:00	\r\n"
        		+ "Palestine	970	+02:00	+03:00\r\n"
        		+ "Panama	507	−05:00	\r\n"
        		+ "Papua New Guinea	675	+10:00\r\n"
//        		+ "to\r\n"
//        		+ "11:00	\r\n"
        		+ "Paraguay	595	−04:00	−03:00\r\n"
        		+ "Peru	51	−05:00	\r\n"
        		+ "Philippines	63	+08:00	\r\n"
        		+ "Pitcairn Islands	64	−08:00	\r\n"
        		+ "Poland	48	+01:00	+02:00\r\n"
        		+ "Portugal	351	+00:00	+01:00\r\n"
        		+ "Puerto Rico	1 (787, 939)	−04:00	\r\n"
        		+ "Qatar	974	+03:00	\r\n"
        		+ "Réunion	262	+04:00	\r\n"
        		+ "Romania	40	+02:00	+03:00\r\n"
        		+ "Russia	7	+02:00\r\n"
//        		+ "to\r\n"
//        		+ "12:00	\r\n"
        		+ "Rwanda	250	+02:00	\r\n"
        		+ "Saba	599 (4)	−04:00	\r\n"
        		+ "Saint Barthélemy	590	−04:00	\r\n"
        		+ "Saint Helena	290	+00:00	\r\n"
        		+ "Saint Kitts and Nevis	1 (869)	−04:00	\r\n"
        		+ "Saint Lucia	1 (758)	−04:00	\r\n"
        		+ "Saint Martin (France)	590	−04:00	\r\n"
        		+ "Saint Pierre and Miquelon	508	−03:00	−02:00\r\n"
        		+ "Saint Vincent and the Grenadines	1 (784)	−04:00	\r\n"
        		+ "Samoa	685	+13:00	+14:00\r\n"
        		+ "San Marino	378	+01:00	+02:00\r\n"
        		+ "São Tomé and Príncipe	239	+00:00	\r\n"
        		+ "Saudi Arabia	966	+03:00	\r\n"
        		+ "Senegal	221	+00:00	\r\n"
        		+ "Serbia	381	+01:00	+02:00\r\n"
        		+ "Seychelles	248	+04:00	\r\n"
        		+ "Sierra Leone	232	+00:00	\r\n"
        		+ "Singapore	65	+08:00	\r\n"
        		+ "Sint Eustatius	599 (3)	−04:00	\r\n"
        		+ "Sint Maarten (Netherlands)	1 (721)	−04:00	\r\n"
        		+ "Slovakia	421	+01:00	+02:00\r\n"
        		+ "Slovenia	386	+01:00	+02:00\r\n"
        		+ "Solomon Islands	677	+11:00	\r\n"
        		+ "Somalia	252	+03:00	\r\n"
        		+ "South Africa	27	+02:00	\r\n"
        		+ "South Georgia and the South Sandwich Islands	500	−02:00	\r\n"
        		+ "South Ossetia	995 (34)	+03:00	\r\n"
        		+ "South Sudan	211	+02:00	\r\n"
        		+ "Spain	34	+01:00	+02:00\r\n"
        		+ "Sri Lanka	94	+05:30	\r\n"
        		+ "Sudan	249	+02:00	\r\n"
        		+ "Suriname	597	−03:00	\r\n"
        		+ "Svalbard	47 (79)	+01:00	+02:00\r\n"
        		+ "Sweden	46	+01:00	+02:00\r\n"
        		+ " Switzerland	41	+01:00	+02:00\r\n"
        		+ "Syria	963	+02:00	+03:00\r\n"
        		+ "Taiwan	886	+08:00	\r\n"
        		+ "Tajikistan	992	+05:00	\r\n"
        		+ "Tanzania	255	+03:00	\r\n"
        		+ "Thailand	66	+07:00	\r\n"
        		+ "Thuraya (Mobile Satellite service)	882 (16)		\r\n"
        		+ "East Timor (Timor-Leste)	670	+09:00	\r\n"
        		+ "Togo	228	+00:00	\r\n"
        		+ "Tokelau	690	+13:00	\r\n"
        		+ "Tonga	676	+13:00	\r\n"
        		+ "Transnistria	373 (2, 5)	+02:00	+03:00\r\n"
        		+ "Trinidad and Tobago	1 (868)	−04:00	\r\n"
        		+ "Tristan da Cunha	290 (8)	+00:00	\r\n"
        		+ "Tunisia	216	+01:00	\r\n"
        		+ "Turkey	90	+03:00	\r\n"
        		+ "Turkmenistan	993	+05:00	\r\n"
        		+ "Turks and Caicos Islands	1 (649)	−05:00	−04:00\r\n"
        		+ "Tuvalu	688	+12:00	\r\n"
        		+ "Uganda	256	+03:00	\r\n"
        		+ "Ukraine	380	+02:00	+03:00\r\n"
        		+ "United Arab Emirates	971	+04:00	\r\n"
        		+ "United Kingdom	44	+00:00	+01:00\r\n"
        		+ "United States	1	−10:00\r\n"
//        		+ "to\r\n"
//        		+ "–05:00	−10:00\r\n"
//        		+ "to\r\n"
//        		+ "–04:00\r\n"
        		+ "Uruguay	598	−03:00	\r\n"
        		+ "US Virgin Islands	1 (340)	−04:00	\r\n"
        		+ "Uzbekistan	998	+05:00	\r\n"
        		+ "Vanuatu	678	+11:00	\r\n"
        		+ "Vatican City State (Holy See)	39 (06698),\r\n"
        		+ "assigned 379	+01:00	+02:00\r\n"
        		+ "Venezuela	58	−04:00	\r\n"
        		+ "Vietnam	84	+07:00	\r\n"
        		+ "Wake Island, USA	1 (808)	+12:00	\r\n"
        		+ "Wallis and Futuna	681	+12:00	\r\n"
        		+ "Yemen	967	+03:00	\r\n"
        		+ "Zambia	260	+02:00	\r\n"
        		+ "Zanzibar	255 (24)	+03:00	\r\n"
        		+ "Zimbabwe	263	+02:00	";
       


        Pattern pattern = Pattern.compile("([a-zA-Z]+)\\s*(\\d+|\\(\\d+\\))?\\s*([\\+|\\−]\\d{2}:\\d{2})?");
        Matcher matcher = pattern.matcher(phoneNumberList);

        List<CountryCodes> countryCodes = new ArrayList<>();

        while (matcher.find()) {
            String country = matcher.group(1);
            String code = matcher.group(2);
            String utcDiff = matcher.group(3);

            //System.out.println("While Country: " + country);
            //System.out.println("While Code: " + (code != null ? code.replaceAll("[\\(\\)]", "") : "N/A"));
            //System.out.println("whileUTC Difference: " + (utcDiff != null ? utcDiff : "N/A"));

            // Create a new CountryCodes instance and add it to the list
            CountryCodes countryInfo = new CountryCodes(country, code != null ? code.replaceAll("[\\(\\)]", "") : "N/A", utcDiff != null ? utcDiff : "N/A");
            countryCodes.add(countryInfo);

            // Add each CountryCodes object to the model individually
            model.addAttribute("whileCountryCodes", countryCodes);
            model.addAttribute("whileCountryCode",(code != null ? code.replaceAll("[\\(\\)]", "") : "N/A"));
        }

        // Add the list of country codes to the model with the appropriate key
        model.addAttribute("forCountryCodes", countryCodes);
    }


    public List<String> getCountryCodes(String phoneNumberList) {
        List<String> countryCodes = new ArrayList<>();
        // Extract country codes from the phone number list
        Pattern pattern = Pattern.compile("([a-zA-Z]+)\\s*(\\d+|\\(\\d+\\))?\\s*([\\+|\\−]\\d{2}:\\d{2})?");
        Matcher matcher = pattern.matcher(phoneNumberList);
        while (matcher.find()) {
            String country = matcher.group(1);
            countryCodes.add(country);
        }
        return countryCodes;
    }

    public void parseAndSetCountryCodes(String phoneNumberList, Model model) {
        List<CountryCodes> countryInfoList = parseCountryCodes(phoneNumberList);
        model.addAttribute("countryInfoList", countryInfoList);
    }

    public static List<CountryCodes> parseCountryCodes(String phoneNumberList) {
        List<CountryCodes> countryInfoList = new ArrayList<>();

        Pattern pattern = Pattern.compile("([a-zA-Z]+)\\s*(\\d+|\\(\\d+\\))?\\s*([\\+|\\−]\\d{2}:\\d{2})?");
        Matcher matcher = pattern.matcher(phoneNumberList);

        while (matcher.find()) {
            String country = matcher.group(1);
            String code = matcher.group(2);
            String utcDiff = matcher.group(3);

            // Create a new CountryCodes object and add it to the list
            CountryCodes countryInfo = new CountryCodes(country, code != null ? code.replaceAll("[\\(\\)]", "") : "N/A", utcDiff != null ? utcDiff : "N/A");
            countryInfoList.add(countryInfo);
        }

        return countryInfoList;
    }
}