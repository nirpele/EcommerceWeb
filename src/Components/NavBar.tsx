import { Link } from 'react-router-dom';


export const NavBar = () => {
  return (
    <div className='navbar'>
      <h1 className='nav-title'>WELCOME ADMIN!</h1>
      <div className='nav-links'>
        <Link to='/login' className='nav-link'>Login</Link>
      </div>
    </div>
  )
}