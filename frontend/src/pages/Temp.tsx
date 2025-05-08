import React from "react";
import { useUserContext } from "../context/UserContext";

interface Props {}

const Temp = () => {
  const { user, role } = useUserContext();
  console.log(user);
  console.log(role);
  return <div></div>;
};

export default Temp;
