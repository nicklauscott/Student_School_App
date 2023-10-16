package com.xtgem.webuild.fstcawka.models.temp

import com.xtgem.webuild.fstcawka.models.enums.NewsCategory

data class TemporalNews(
    val category: NewsCategory,
    val title: String,
    val description: String,
    val content: String
)

val news = listOf(
    TemporalNews(
        category = NewsCategory.Administration,
        title = "SCHOOL FEES FOR FIRST TERM 2023/2024 SESSION",
        description = "SCHOOL FEES FOR FIRST TERM 2023/2024 SESSION",
        content = "1ST TERM SCHOOL FESS: JS1: ₦100,000, JSS 2 : ₦72,000, JSS 3: ₦87,000, \" +\n" +
                "            \"SS1/ST1: ₦97,000, SS2/ST2: ₦72,000, SS3/ST3: ₦ 119,000, (DAY students: less \" +\n" +
                "            \"₦30,000 from the class bill)"
    ),
    TemporalNews(
        category = NewsCategory.Academic,
        title = "RESUMPTION",
        description = "RESUMPTION",
        content = "\n" +
                "\n" +
                "RESUMPTION DATES FOR 3RD TERM 2022/2023 ACADEMIC SESSION\n" +
                "\n" +
                "1. JUNIOR STUDENTS: 29/04/2023\n" +
                "\n" +
                "2. SENIOR STUDENTS: 30/04/2023\n"
    ),
    TemporalNews(
        category = NewsCategory.Others,
        title = "World News",
        description = "Russia to build nuclear plant to meet Burkina Faso's energy needs",
        content = "The new deal with Russia is a culmination of talks the Burkinabe military ruler, Capt Ibrahim Traore, had with Russian President Vladimir Putin in July during the Russia-Africa summit in Moscow.\n" +
                "\n" +
                "Capt Traore requested President Putin's support in setting up a nuclear power plant in Burkina Faso, which he said would help meet the country's energy demands and those of neighbouring countries.\n" +
                "\n" +
                "\"We have a critical need for energy, this is an important point for me because we need, if possible, to build a nuclear power station in Burkina Faso to produce electricity,\" he was quoted as saying at the time.\n" +
                "\n" +
                "\"Our position is rather strategic because we are in the heart of West Africa and we have an energy deficit in the sub-region.\"\n" +
                "\n" +
                "The deal is part of Burkina Faso's target to achieve 95% electricity access for urban areas and 50% for rural areas by 2030.\n" +
                "\n" +
                "Burkina Faso gets most of its electricity from biofuels like charcoal and wood while oil products account for one-third of the total energy supply, according to the International Energy Agency.\n" +
                "\n" +
                "According to the US development agency USAid, Burkina Faso also has one of the highest electricity costs in Africa.\n" +
                "\n" +
                "South Africa is currently the only African state that produces nuclear power commercially, but increasingly more nations on the continent are moving in the same direction.\n" +
                "\n" +
                "Russia is helping Egypt to build a nuclear power plant at cost of \$30bn (£24bn) following a deal signed by President Abdel Fattah al-Sisi and President Putin in 2017.\n" +
                "\n" +
                "Russia also signed a deal to build power plants in Nigeria in the same year, but the project is yet to begin.\n" +
                "\n" +
                "Kenya has also announced plans to build its first nuclear power plant by 2027, but it is still to decide on its international partner.\n" +
                "\n" +
                "In September this year, Rwanda announced that it had opted for Canadian-German company Dual Fluid Energy to build a nuclear reactor by 2028.\n" +
                "\n" +
                "The Rwandan government said the reactor will be instrumental to meet the central African country's energy demands and \"build resilience\" as a result of climate change."
    ),
    TemporalNews(
        category = NewsCategory.Extra,
        title = "Middle East News",
        description = "BBC journalists covering the attack on Israel were assaulted and held at gunpoint after they were stopped by police in the Israeli city of Tel Aviv.",
        content = "Muhannad Tutunji, Haitham Abudiab and their BBC Arabic team were driving to a hotel when their car was intercepted.\n" +
                "\n" +
                "They were dragged from the vehicle - marked \"TV\" in red tape - searched and pushed against a wall.\n" +
                "\n" +
                "A BBC spokesperson said journalists \"must be able to report on the conflict in Israel-Gaza freely\".\n" +
                "\n" +
                "Mr Tutunji and Mr Abudiab said they identified themselves as BBC journalists and showed police their press ID cards.\n" +
                "\n" +
                "While attempting to film the incident, Mr Tutunji said his phone was thrown on the ground and he was struck on the neck.\n" +
                "\n" +
                "\"One of our BBC News Arabic teams deployed in Tel Aviv, in a vehicle clearly marked as media, was stopped and assaulted last night by Israeli police. Journalists must be able to report on the conflict in Israel-Gaza freely,\" a BBC spokesperson said.\n" +
                "\n" +
                "The BBC has contacted Israeli police for comment.\n" +
                "\n" +
                "Palestinian militant group Hamas launched an unprecedented attack on Israel on Saturday, killing at least 1,300 people.\n" +
                "\n" +
                "More than 1,400 people have been killed in Gaza since Israel launched retaliatory air strikes. A ground offensive is also expected.\n" +
                "\n" +
                "Israel has told those in the north of the Gaza Strip - about 1.1 million people - to relocate to the south of the territory within 24 hours.\n" +
                "\n" +
                "The UN has urged Israel to withdraw the order, warning of \"devastating humanitarian consequences\".\n" +
                "\n" +
                "Hamas, which controls the Gaza Strip, told civilians to ignore the evacuation order, describing it as \"fake propaganda\"."
    ),
    TemporalNews(
        category = NewsCategory.Extra,
        title = "Technology News",
        description = "Microsoft completes blockbuster Activision Blizzard takeover after UK removes final hurdle ",
        content = " Microsoft has completed its takeover of Activision Blizzard, the maker of “Call of Duty” and other hit video games, closing one of the biggest tech deals of all time.\n" +
                "\n" +
                "The company said in a filing Friday with the US Securities and Exchange Commission that Activision (ATVI) had now become a wholly-owned subsidiary.\n" +
                "\n" +
                "Earlier on Friday, UK antitrust officials approved the planned acquisition, removing the final regulatory hurdle to the deal closing.\n" +
                "\n" +
                "The Competition and Markets Authority said the merger had been cleared after the companies agreed to give up certain cloud gaming rights. The concession is “a game-changer” that will allow “competitive prices and better services,” the CMA said in a statement.\n" +
                "\n" +
                "Microsoft (MSFT) unveiled the deal in early 2022, but it was blocked in April by the UK competition regulator.\n" +
                "\n" +
                "The CMA was the only regulator worldwide standing in the way of the landmark acquisition, which was valued at \$69 billion when it was first announced.\n" +
                "\n" +
                "The UK regulator had concerns about competition in the cloud gaming market, saying Microsoft could seek to make Activision’s games exclusive to its own platforms, and then increase the cost of user subscriptions, leaving gamers with less choice. " +
                " In August, Microsoft and Activision addressed those concerns by revising the deal.\n" +
                "\n" +
                "They proposed a restructured merger, which would allow Activision’s cloud streaming rights outside the European Union and three other European countries to be sold to a rival, Ubisoft Entertainment.\n" +
                "\n" +
                "That appeased the CMA, which signaled last month that it would most likely approve the reworked takeover.\n" +
                "\n" +
                "“The new deal will stop Microsoft from locking up competition in cloud gaming,” the agency said Friday.\n" +
                "\n" +
                "“It will also help to ensure that cloud gaming providers will be able to use non-Windows operating systems for Activision content, reducing costs and increasing efficiency.”\n" +
                "\n" +
                "Activision Blizzard is one of the world’s biggest video game developers. Alongside “Call of Duty,” it also produces “World of Warcraft” and “Overwatch.”\n" +
                "\n" +
                "Microsoft, which sells the Xbox gaming console, offers a popular video game subscription service called Xbox Game Pass, as well as a cloud-based video game streaming service.\n" +
                "\n" +
                "The acquisition is expected to help Microsoft boost its standing in the gaming industry and better compete with market leaders Tencent and Sony.\n" +
                "\n" +
                "In a statement on X, the platform formerly known as Twitter, Microsoft President Brad Smith said “we’re grateful for the CMA’s thorough review and decision.” "
    ),
    TemporalNews(
        category = NewsCategory.Extra,
        title = "Politics",
        description = "Trump loses first of several bids to toss suit seeking to block him from Colorado ballot ",
        content = " Former President Donald Trump has lost the first of several attempts to throw out a lawsuit that seeks to block him from the 2024 presidential ballot in Colorado, based on the 14th Amendment’s prohibition against insurrectionists holding public office.\n" +
                "\n" +
                "Colorado District Judge Sarah Wallace this week rejected Trump’s bid to get the lawsuit dismissed on free-speech grounds.\n" +
                "\n" +
                "The former president still has several pending challenges against the case, which was initiated by a liberal government watchdog group.\n" +
                "\n" +
                "A trial to determine Trump’s eligibility is set for October 30, if the case reaches that stage. Colorado election officials say there’s a “hard deadline” to resolve the dispute before January 5, when the ballot printing process begins for the March 5 Republican primary.\n" +
                "\n" +
                "A post-Civil War provision of the 14th Amendment says American officials who take an oath to uphold the Constitution are disqualified from future office if they “engaged in insurrection or rebellion” or if they have “given aid or comfort” to insurrectionists. But the Constitution does not spell out how to enforce this ban, and it has been applied only twice since the late 1800s, when it was used against former Confederates.\n" +
                "\n" +
                "In a 22-page ruling, Wallace said she wasn’t swayed by Trump’s argument that the lawsuit seeks to improperly restrict his rights to participate in the political process.\n" +
                "\n" +
                "“The Court has no difficulty concluding that it is to the benefit of the general public that, regardless of political affiliation, only constitutionally qualified candidates are placed on the ballot,” Wallace wrote.\n" +
                "\n" +
                "She added that resolving the question of Trump’s eligibility is particularly important because he is seeking “the highest office in the country” and “the disqualification sought is based on allegations of insurrection against the very government over which the candidate seeks to preside.”\n" +
                "\n" +
                "Trump denies wrongdoing and says the candidacy challenges are meritless. The Trump campaign did not immediately respond to a request for comment on the ruling. "
    )
)

