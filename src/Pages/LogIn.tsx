import { useState, useCallback, FormEvent, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../Service/UseContext/UserContext";
import { fetchCustomerIdByEmail } from "../Service/Service";



function LogIn(): JSX.Element {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const [loginToken, setLoginToken] = useState("");
  const [logId, setLogId] = useState<number | undefined>();
  const userContext = useContext(UserContext);
  const navigate = useNavigate();

  console.log(userContext);
  useEffect(() => {
    userContext.setUser({
      email: email,
      password: password,
      userType: userType,
      token: loginToken,
      id: logId,
    });
  }, [email, userType, password, loginToken, logId]);

  const login = useCallback(async (event: FormEvent<HTMLFormElement>): Promise<void> => {
      event.preventDefault();
      setUserType(email === "admin@admin" ? "ADMINISTRATOR" : "CUSTOMER");
      if (userType !== "") {
        try {
          const response = await fetch("http://localhost:8080/login", {
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              email: email,
              password: password,
              userType: userType,
            }),
            mode: "cors",
            method: "POST",
          });

          const token = await response.text();
          setLoginToken(token);

        } catch (error) {
          console.log(error);
        }
      }
    },
    [email, userType, password]
  );

  useEffect(() => {
    if (loginToken !== "" && userType === "CUSTOMER") {
      if (loginToken !== "user details not valid") {
        fetchCustomerIdByEmail(email).then((id) => setLogId(id));
        console.log(loginToken);
      }
    }
  }, [loginToken, userType, email]);

  const navigateToHomeAdmin = () => {
    navigate("/admin");
  };

  const navigateToHomeCustomer = () => {
    navigate(`/customer/${logId}`);
  };

  const renderLoggedInUser = () => {
    if (loginToken === "") {
      return (
        <form onSubmit={login}>
          <label>Email: </label>
          <input
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
          />
          <label>Password: </label>
          <input
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
          />
          <button type="submit">Login</button>
        </form>
      );
    } else if (loginToken !== "user details not valid") {
      if (userType === "ADMINISTRATOR") {
        navigateToHomeAdmin();
      } else if (userType === "CUSTOMER" && logId !== undefined) {
        navigateToHomeCustomer();
      } else {
        return <p>Loading...</p>;
      }
    } else {
      alert("The email, password, or type are wrong.");
      return null;
    }
  };

  return (
    <div className="login">
      <button type="button" onClick={()=>navigate(-1)}> back </button><br />
      {renderLoggedInUser()}
    </div>
  );
}

export default LogIn;