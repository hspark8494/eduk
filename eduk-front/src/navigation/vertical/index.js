/*

Array of object

Top level object can be:
1. Header
2. Group (Group can have navItems as children)
3. navItem

* Supported Options

/--- Header ---/

header

/--- nav Grp ---/

title
icon (if it's on top level)
tag
tagVariant
children

/--- nav Item ---/

icon (if it's on top level)
title
route: [route_obj/route_name] (I have to resolve name somehow from the route obj)
tag
tagVariant

*/
//import dashboard from './dashboard'
//import home from './home'
//import channeles from './channeles'
//import appsAndPages from './apps-and-pages'
//import others from './others'
//import chartsAndMaps from './charts-and-maps'
//import uiElements from './ui-elements'
//import formAndTable from './forms-and-table'

// Array of sections
//export default [...dashboard, ...appsAndPages, ...uiElements, ...formAndTable, ...chartsAndMaps, ...others]
export default [
    {
        title: '홈',
        route: 'Home',
        icon: 'HomeIcon',
    }, {
        title: '강의실',
        route: 'lesson',
        icon: 'VideoIcon',
    },
    {
        title: '게시판',
        route: 'post-list',
        icon: 'Edit2Icon',
    },
    {
        title: '일정',
        route: 'apps-calendar',
        icon: 'CalendarIcon',
    },
    {
        title: '자료실',
        route: 'forms-element-file-input',
        icon: "HardDriveIcon"
    },
    {
        title: '멤버',
        route: 'User',
        icon: 'UsersIcon',
    },
    {
        title: '설정',
        route: 'pages-account-setting',
        icon: 'SettingsIcon'
    },]