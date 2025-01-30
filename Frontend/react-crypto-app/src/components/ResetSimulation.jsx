import React from "react";
import { HandleReset } from "@/functions/HandleReset";

const ResetSimulation = ({}) => {
  return (
    <div id="content">
      <button className="resetButton" onClick={HandleReset}>Reset Simulation</button>
    </div>
  );
};

export default ResetSimulation;