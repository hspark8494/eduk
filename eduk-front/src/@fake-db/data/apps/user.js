import mock from '@/@fake-db/mock'
import { paginateArray, sortCompare } from '@/@fake-db/utils'

/* eslint-disable global-require */
const data = {
  users: [
    {
      id: 1,
      fullName: '이기현',
      company: 'Yotz PVT LTD',
      role: '강사',
      username: 'gslixby0',
      country: 'El Salvador',
      contact: '(479) 232-9151',
      email: 'gihyun@gmail.com',
      currentPlan: 'enterprise',
      status: 'inactive',
      avatar: require('@/assets/images/avatars/profile-4.jpg'),
    },
    {
      id: 2,
      fullName: '김규리',
      company: 'Skinder PVT LTD',
      role: '학생',
      username: 'hredmore1',
      country: 'Albania',
      contact: '(472) 607-9137',
      email: 'kkl0219kkl@naver.com',
      currentPlan: 'team',
      status: 'pending',
      avatar: require('@/assets/images/avatars/profile-3.jpg'),
    },
    {
      id: 3,
      fullName: '김성규',
      company: 'Skinder PVT LTD',
      role: '학생',
      username: 'hredmore1',
      country: 'Albania',
      contact: '(472) 607-9137',
      email: 'rlatjdrb950@naver.com',
      currentPlan: 'team',
      status: 'pending',
    },
    {
      id: 4,
      fullName: '박성하',
      company: 'Skinder PVT LTD',
      role: '학생',
      username: 'hredmore1',
      country: 'Albania',
      contact: '(472) 607-9137',
      email: '100sungha@naver.com',
      currentPlan: 'team',
      status: 'pending',
    },
    {
      id: 5,
      fullName: '김연식',
      company: 'Skinder PVT LTD',
      role: '학생',
      username: 'hredmore1',
      country: 'Albania',
      contact: '(472) 607-9137',
      email: 'kkl0219kkl@naver.com',
      currentPlan: 'team',
      status: 'pending',
      avatar: require('@/assets/images/avatars/profile-1.png'),
    },
    {
      id: 6,
      fullName: '박효승',
      company: 'Skinder PVT LTD',
      role: '학생',
      username: 'hredmore1',
      country: 'Albania',
      contact: '(472) 607-9137',
      email: 'hspark8494@gmail.com',
      currentPlan: 'team',
      status: 'pending',
      avatar: require('@/assets/images/avatars/profile-2.png'),
    },
    {
      id: 7,
      fullName: '장희정',
      company: 'Skinder PVT LTD',
      role: '학생',
      username: 'hredmore1',
      country: 'Albania',
      contact: '(472) 607-9137',
      email: '8253jang@daum.net',
      currentPlan: 'team',
      status: 'pending',
    },
  ],
}
/* eslint-enable */

// ------------------------------------------------
// GET: Return Users
// ------------------------------------------------
mock.onGet('/apps/user/users').reply(config => {
  // eslint-disable-next-line object-curly-newline
  const {
    q = '',
    perPage = 10,
    page = 1,
    sortBy = 'id',
    sortDesc = false,
    role = null,
    plan = null,
    status = null,
  } = config.params
  /* eslint-enable */

  const queryLowered = q.toLowerCase()
  const filteredData = data.users.filter(
    user =>
      /* eslint-disable operator-linebreak, implicit-arrow-linebreak */
      (user.username.toLowerCase().includes(queryLowered) || user.fullName.toLowerCase().includes(queryLowered)) &&
      user.role === (role || user.role) &&
      user.currentPlan === (plan || user.currentPlan) &&
      user.status === (status || user.status),
  )
  /* eslint-enable  */

  const sortedData = filteredData.sort(sortCompare(sortBy))
  if (sortDesc) sortedData.reverse()

  return [
    200,
    {
      users: paginateArray(sortedData, perPage, page),
      total: filteredData.length,
    },
  ]
})

// ------------------------------------------------
// POST: Add new user
// ------------------------------------------------
mock.onPost('/apps/user/users').reply(config => {
  // Get event from post data
  const { user } = JSON.parse(config.data)

  // Assign Status
  user.status = 'active'

  const { length } = data.users
  let lastIndex = 0
  if (length) {
    lastIndex = data.users[length - 1].id
  }
  user.id = lastIndex + 1

  data.users.push(user)

  return [201, { user }]
})

// ------------------------------------------------
// GET: Return Single User
// ------------------------------------------------
mock.onGet(/\/apps\/user\/users\/\d+/).reply(config => {
  // Get event id from URL
  let userId = config.url.substring(config.url.lastIndexOf('/') + 1)

  // Convert Id to number
  userId = Number(userId)

  const userIndex = data.users.findIndex(e => e.id === userId)
  const user = data.users[userIndex]

  if (user) return [200, user]
  return [404]
})
