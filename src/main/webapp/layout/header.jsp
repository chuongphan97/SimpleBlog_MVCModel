<!-- Navbar -->
<div class='navbar navbar-default' id='navbar'>
    <a class='navbar-brand' href='#'>
        <i class='icon-beer'></i>
        Hierapolis
    </a>
    <ul class='nav navbar-nav pull-right'>

        <li class='dropdown user'>
            <a class='dropdown-toggle' data-toggle='dropdown' href='#'>
                <i class='icon-user'></i>
                <strong>${account.getUsername()}</strong>
                <img class="img-rounded" src="http://placehold.it/20x20/ccc/777" />
                <b class='caret'></b>
            </a>
            <ul class='dropdown-menu'>

                <li>
                    <a href="/blogs?action=home">Home page</a>
                </li>
                <li>
                    <a href="/blogs?action=logout">Log out</a>
                </li>
            </ul>
        </li>
    </ul>
</div>
<div id='wrapper'>
    <!-- Sidebar -->
    <section id='sidebar'>
        <i class='icon-align-justify icon-large' id='toggle'></i>
        <ul id='dock'>
            <li class='active launcher'>
                <i class='icon-dashboard'></i>
                <a href="dashboard.html">Overview</a>
            </li>

            <li class='launcher dropdown hover'>
                <i class='icon-flag'></i>
                <a href='#'>Reports</a>
                <ul class='dropdown-menu'>
                    <li class='dropdown-header'>Actions</li>
                    <li>
                        <a href='/blogs?action=create'>Add new Ad-post</a>
                    </li>
                    <li>
                        <a href='#'>Another action</a>
                    </li>
                </ul>
            </li>

        </ul>
        <div data-toggle='tooltip' id='beaker' title='Made by lab2023'></div>
    </section>
