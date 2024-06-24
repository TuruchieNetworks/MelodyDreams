window.onload = function() {
    getCountryDetails();
};

let countryList;

async function getCountryDetails() {
    try {
    var countryDetails = `
        Serving    Code    Time (UTC ±)
        Afghanistan    93    +04:30    
        Åland    358 (18)    +02:00    +03:00
        Albania    355    +01:00    +02:00
        Algeria    213    +01:00    
        American Samoa    1 (684)    −11:00    
        Andorra    376    +01:00    +02:00
        Angola    244    +01:00    
        Anguilla    1 (264)    −04:00    
        Antigua and Barbuda    1 (268)    −04:00    
        Argentina    54    −03:00    
        Armenia    374    +04:00    
        Aruba    297    −04:00    
        Ascension    247    +00:00    
        Australia    61    +08:00
        to
        10:30    +08:00
        to
        11:00
        Austria    43    +01:00    +02:00
        Azerbaijan    994    +04:00    
        Bahamas    1 (242)    −05:00    −04:00
        Bahrain    973    +03:00    
        Bangladesh    880    +06:00    
        Barbados    1 (246)    −04:00    
        Belarus    375    +03:00    
        Belgium    32    +01:00    +02:00
        Belize    501    −06:00    
        Benin    229    +01:00    
        Bermuda    1 (441)    −04:00    −03:00
        Bhutan    975    +06:00    
        Bolivia    591    −04:00    
        Bonaire    599 (7)    −04:00    
        Bosnia and Herzegovina    387    +01:00    +02:00
        Botswana    267    +02:00    
        Brazil    55    −05:00
        to
        –02:00    
        British Indian Ocean Territory (Diego Garcia)    246    +06:00    
        British Virgin Islands    1 (284)    −04:00    
        Brunei Darussalam    673    +08:00    
        Bulgaria    359    +02:00    +03:00
        Burkina Faso    226    +00:00    
        Burundi    257    +02:00    
        Cape Verde    238    −01:00    
        Cambodia    855    +07:00    
        Cameroon    237    +01:00    
        Canada    1    −08:00
        to
        –03:30    −07:00
        to
        –02:30
        Caribbean Netherlands    599 (3,4,7)    −04:00    
        Cayman Islands    1 (345)    −05:00    
        Central African Republic    236    +01:00    
        Chad    235    +01:00    
        Chile    56    −06:00
        to
        –04:00    −05:00
        to
        –03:00
        China    86    +08:00    
        Christmas Island    61 (89164)    +07:00    
        Cocos (Keeling) Islands    61 (89162)    +06:30    
        Colombia    57    −05:00    
        Comoros    269    +03:00    
        Congo    242    +01:00    
        Congo, Democratic Republic of the    243    +01:00
        to
        02:00    
        Cook Islands    682    −10:00    
        Costa Rica    506    −06:00    
        Ivory Coast (Côte d'Ivoire)    225    +00:00    
        Croatia    385    +01:00    +02:00
        Cuba    53    −05:00    −04:00
        Curaçao    599 (9)    −04:00    
        Cyprus    357    +02:00    +03:00
        Czech Republic    420    +01:00    +02:00
        Denmark    45    +01:00    +02:00
        Djibouti    253    +03:00    
        Dominica    1 (767)    −04:00    
        Dominican Republic    1 (809, 829, 849)    −04:00    
        Easter Island    56    −06:00    −05:00
        Ecuador    593    −06:00
        to
        –05:00    
        Egypt    20    +02:00    +03:00
        El Salvador    503    −06:00    
        Ellipso (Mobile Satellite service)    881 (2, 3)        
        EMSAT (Mobile Satellite service)    882 (13)        
        Equatorial Guinea    240    +01:00    
        Eritrea    291    +03:00    
        Estonia    372    +02:00    +03:00
        Eswatini    268    +02:00    
        Ethiopia    251    +03:00    
        Falkland Islands    500    −03:00    
        Faroe Islands    298    +00:00    +01:00
        Fiji    679    +12:00    +13:00
        Finland    358    +02:00    +03:00
        France    33    +01:00    +02:00
        French Antilles    596        
        French Guiana    594    −03:00    
        French Polynesia    689    −10:00
        to
        –09:00    
        Gabon    241    +01:00    
        Gambia    220    +00:00    
        Georgia    995    +04:00    
        Germany    49    +01:00    +02:00
        Ghana    233    +00:00    
        Gibraltar    350    +01:00    +02:00
        Global Mobile Satellite System (GMSS)    881        
        Globalstar (Mobile Satellite Service)    881 (8, 9)        
        Greece    30    +02:00    +03:00
        Greenland    299    −04:00
        to
        01:00    −03:00
        to
        00:00
        Grenada    1 (473)    −04:00    
        Guadeloupe    590    −04:00    
        Guam    1 (671)    +10:00    
        Guatemala    502    −06:00    
        Guernsey    44 (1481, 7781, 7839, 7911)    +00:00    +01:00
        Guinea    224    +00:00    
        Guinea-Bissau    245    +00:00    
        Guyana    592    −04:00    
        Haiti    509    −05:00    −04:00
        Honduras    504    −06:00    
        Hong Kong    852    +08:00    
        Hungary    36    +01:00    +02:00
        Iceland    354    +00:00    
        ICO Global (Mobile Satellite Service)    881 (0, 1)        
        India    91    +05:30    
        Indonesia    62    +07:00
        to
        09:00    
        Inmarsat SNAC    870        
        International Freephone Service (UIFN)    800        
        International Networks    882, 883        
        International Premium Rate Service    979        
        International Shared Cost Service (ISCS)    808        
        Iran    98    +03:30    +04:30
        Iraq    964    +03:00    
        Ireland    353    +00:00    +01:00
        Iridium (Mobile Satellite service)    881 (6, 7)        
        Isle of Man    44 (1624, 7524, 7624, 7924)    +00:00    +01:00
        Israel    972    +02:00    +03:00
        Italy    39    +01:00    +02:00
        Jamaica    1 (658, 876)    −05:00    
        Jan Mayen    47 (79)    +01:00    +02:00
        Japan    81    +09:00    
        Jersey    44 (1534)    +00:00    +01:00
        Jordan    962    +02:00    +03:00
        Kazakhstan    7 (6, 7) (997 assigned but now abandoned)    +05:00
        to
        06:00    
        Kenya    254    +03:00    
        Kiribati    686    +12:00
        to
        14:00    
        Korea, North    850    +09:00    
        Korea, South    82    +09:00    
        Kosovo    383    +01:00    +02:00
        Kuwait    965    +03:00    
        Kyrgyzstan    996    +06:00    
        Laos    856    +07:00    
        Latvia    371    +02:00    +03:00
        Lebanon    961    +02:00    +03:00
        Lesotho    266    +02:00    
        Liberia    231    +00:00    
        Libya    218    +02:00    
        Liechtenstein    423    +01:00    +02:00
        Lithuania    370    +02:00    +03:00
        Luxembourg    352    +01:00    +02:00
        Macau    853    +08:00    
        Madagascar    261    +03:00    
        Malawi    265    +02:00    
        Malaysia    60    +08:00    
        Maldives    960    +05:00    
        Mali    223    +00:00    
        Malta    356    +01:00    +02:00
        Marshall Islands    692    +12:00    
        Martinique    596    −04:00    
        Mauritania    222    +00:00    
        Mauritius    230    +04:00    
        Mayotte    262 (269, 639)    +03:00    
        Mexico    52    −08:00
        to
        −05:00    −07:00
        to
        −05:00
        Micronesia, Federated States of    691    +10:00
        to
        11:00    
        Moldova    373    +02:00    +03:00
        Monaco    377    +01:00    +02:00
        Mongolia    976    +07:00
        to
        08:00    
        Montenegro    382    +01:00    +02:00
        Montserrat    1 (664)    −04:00    
        Morocco    212    +01:00    
        Mozambique    258    +02:00    
        Myanmar    95    +06:30    
        Namibia    264    +02:00    
        Nauru    674    +12:00    
        Nepal    977    +05:45    
        Netherlands    31    +01:00    +02:00
        Nevis    1 (869)    −04:00    
        New Caledonia    687    +11:00    
        New Zealand    64    +12:00    +13:00
        Nicaragua    505    −06:00    
        Niger    227    +01:00    
        Nigeria    234    +01:00    
        Niue    683    −11:00    
        Norfolk Island    672 (3)    +11:00    
        North Macedonia    389    +01:00    +02:00
        Northern Cyprus    90 (392)    +02:00    +03:00
        Northern Ireland    44 (28)    +00:00    +01:00
        Northern Mariana Islands    1 (670)    +10:00    
        Norway    47    +01:00    +02:00
        Oman    968    +04:00    
        Pakistan    92    +05:00    
        Palau    680    +09:00    
        Palestine    970    +02:00    +03:00
        Panama    507    −05:00    
        Papua New Guinea    675    +10:00
        to
        11:00    
        Paraguay    595    −04:00    −03:00
        Peru    51    −05:00    
        Philippines    63    +08:00    
        Pitcairn Islands    64    −08:00    
        Poland    48    +01:00    +02:00
        Portugal    351    +00:00    +01:00
        Puerto Rico    1 (787, 939)    −04:00    
        Qatar    974    +03:00    
        Réunion    262    +04:00    
        Romania    40    +02:00    +03:00
        Russia    7    +02:00
        to
        12:00    
        Rwanda    250    +02:00    
        Saba    599 (4)    −04:00    
        Saint Barthélemy    590    −04:00    
        Saint Helena    290    +00:00    
        Saint Kitts and Nevis    1 (869)    −04:00    
        Saint Lucia    1 (758)    −04:00    
        Saint Martin (France)    590    −04:00    
        Saint Pierre and Miquelon    508    −03:00    −02:00
        Saint Vincent and the Grenadines    1 (784)    −04:00    
        Samoa    685    +13:00    +14:00
        San Marino    378    +01:00    +02:00
        São Tomé and Príncipe    239    +00:00    
        Saudi Arabia    966    +03:00    
        Senegal    221    +00:00    
        Serbia    381    +01:00    +02:00
        Seychelles    248    +04:00    
        Sierra Leone    232    +00:00    
        Singapore    65    +08:00    
        Sint Eustatius    599 (3)    −04:00    
        Sint Maarten (Netherlands)    1 (721)    −04:00    
        Slovakia    421    +01:00    +02:00
        Slovenia    386    +01:00    +02:00
        Solomon Islands    677    +11:00    
        Somalia    252    +03:00    
        South Africa    27    +02:00    
        South Georgia and the South Sandwich Islands    500    −02:00    
        South Ossetia    995 (34)    +03:00    
        South Sudan    211    +02:00    
        Spain    34    +01:00    +02:00
        Sri Lanka    94    +05:30    
        Sudan    249    +02:00    
        Suriname    597    −03:00    
        Svalbard    47 (79)    +01:00    +02:00
        Sweden    46    +01:00    +02:00
        Switzerland    41    +01:00    +02:00
        Syria    963    +02:00    +03:00
        Taiwan    886    +08:00    
        Tajikistan    992    +05:00    
        Tanzania    255    +03:00    
        Thailand    66    +07:00    
        Thuraya (Mobile Satellite service)    882 (16)        
        East Timor (Timor-Leste)    670    +09:00    
        Togo    228    +00:00    
        Tokelau    690    +13:00    
        Tonga    676    +13:00    
        Transnistria    373 (2, 5)    +02:00    +03:00
        Trinidad and Tobago    1 (868)    −04:00    
        Tristan da Cunha    290 (8)    +00:00    
        Tunisia    216    +01:00    
        Turkey    90    +03:00    
        Turkmenistan    993    +05:00    
        Turks and Caicos Islands    1 (649)    −05:00    −04:00
        Tuvalu    688    +12:00    
        Uganda    256    +03:00    
        Ukraine    380    +02:00    +03:00
        United Arab Emirates    971    +04:00    
        United Kingdom    44    +00:00    +01:00
        United States    1    −10:00
        to
        −05:00    −10:00
        to
        −04:00
        Uruguay    598    −03:00    
        US Virgin Islands    1 (340)    −04:00    
        Uzbekistan    998    +05:00    
        Vanuatu    678    +11:00    
        Vatican City State (Holy See)    39 (06698),
        assigned 379    +01:00    +02:00
        Venezuela    58    −04:00    
        Vietnam    84    +07:00    
        Wake Island, USA    1 (808)    +12:00    
        Wallis and Futuna    681    +12:00    
        Yemen    967    +03:00    
        Zambia    260    +02:00    
        Zanzibar    255 (24)    +03:00    
        Zimbabwe    263    +02:00 `   
    // Split the content into lines
    var lines = countryDetails.trim().split('\n');

        // Initialize an array to store country data
        const countries = [];

        // Iterate over each line
        lines.forEach(function(line) {
            // Split each line by whitespace
            const parts = line.trim().split(/\s+/);

            // Extract country name, code, and UTC difference
            let name = "";
            let code = "";
            let utcDiff = "";
            let parenthesesIndex = -1;

            // Check if there are parentheses in the line
            const hasParentheses = line.includes('(') && line.includes(')');

            // Find the index of "to" if present
            const toIndex = parts.indexOf("to");

            // Check if "to" is present and extract data accordingly
            if (toIndex !== -1) {
                // Extract name and code
                name = parts.slice(0, toIndex).join(" ");
                code = parts[toIndex - 1];

                // Extract UTC difference based on "to" position
                if (toIndex === 1) {
                    utcDiff = parts[toIndex + 1];
                } else {
                    utcDiff = parts[toIndex + 1] + " to " + parts[toIndex + 3];
                }
            } else if (hasParentheses) {
                // Find the index of the first parentheses
                parenthesesIndex = parts.findIndex(part => part.includes('('));
                // Extract name, code, and UTC difference accordingly
                name = parts.slice(0, parenthesesIndex).join(" ");
                code = parts[parenthesesIndex].replace('(', '').replace(')', '');
                utcDiff = parts[parenthesesIndex + 1];
            } else {
                // Extract name, code, and UTC difference normally
                name = parts.slice(0, -3).join(" ");
                code = parts[parts.length - 3];
                utcDiff = parts.slice(-2).join(" ");
            }

            // Create a country object and add it to the countries array
            const country = {
                name: name,
                code: code,
                utcDiff: utcDiff
            };
            countries.push(country);
        });

        // Print the countries array to the console
        console.log(countries);

        // Render the countries
        renderCountries(countries);

        // Set the countryList variable
        countryList = countries;

        // Call the function to populate countries somewhere else
        populateCountries();
    } catch (error) {
        console.error('Error fetching country details:', error);
    }
}

function renderCountries(countries) {
    const countriesDiv = document.getElementById("countries");

    // Clear previous list
    countriesDiv.innerHTML = '';

    // Iterate over each country and create a div for it
    countries.forEach(function(country) {
        const countryDiv = document.createElement("div");
        const countryText = document.createElement("p");
        countryText.textContent = `${country.name} (${country.code}) - UTC ${country.utcDiff}`;
        countryDiv.appendChild(countryText);
        countriesDiv.appendChild(countryDiv);

        // Add styles to the country div if needed
        countryDiv.classList.add('country-item');
    });
}

function populateCountries() {
    // Access the countryList variable here
    console.log(countryList);
    return countryList;
}

function updateContactNumber(inputId, countryCode) {
    var contactInput = document.getElementById(inputId);
    var countryCodeSelect = document.getElementById(countryCode);
        
    // Add event listener to the country code select element
    countryCodeSelect.addEventListener('change', function() {
        // Get the selected country code
        var selectedCountryCode = this.value;
            
        // Update the hidden input value with the concatenated country code and contact number
        contactInput.value = selectedCountryCode + contactInput.value;
    });
}

//-- Assuming you have a select element with id "countryCode" and input field with id "contactNumber" -->

updateContactNumber('contactNumber', 'countryCode');

var contactInput = document.getElementById('contactNumberInput');
var countryCodeSelect = document.getElementById('countryCode');
var contactDisplay = document.getElementById('contactNumberDisplay');
    
// Add event listener to the country code select element
countryCodeSelect.addEventListener('change', function() {
        // Get the selected country code
        var selectedCountryCode = this.value;
        
        // Update the hidden input value with the concatenated country code and contact number
        contactInput.value = selectedCountryCode + contactInput.value;
        
        // Update the display area with the concatenated country code and contact number
        contactDisplay.innerText = contactInput.value;
    });
